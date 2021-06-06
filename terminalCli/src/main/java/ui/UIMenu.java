package terminalcli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import commands.Command;

public class UIMenu implements UIElement {

	private String name;
	private List<UIElement> options;
	private int numberOfOptions;
	private char exitKey = 'R';
	private String exitText = "Volver a pantalla anterior";
	private Command action;
	
	public UIMenu(String name)
	{
		this.name = name;
		numberOfOptions = 0;
		options = new ArrayList<UIElement>();
	}
	
	public UIMenu(String name, String exitText, char exitKey)
	{
		this(name);
		this.exitText = exitText;
		this.exitKey = exitKey;
	}
	
	public UIMenu(String name, Command action)
	{
		this(name);
		this.action = action;
	}
	
	public void click()
	{
		Scanner keyboard = new Scanner(System.in);
		String option;
		int numericOption;
		do
		{
			// If this menu has an associated action, run it before writing to screen
			if (action != null)	action.execute();
			printMenu();
			
			option = keyboard.nextLine();
			if (isExitKey(option) == false)
			{
				try {
					numericOption = Integer.parseInt(option);					
				} catch (NumberFormatException ex)
				{
					numericOption = 0;
				}
				
				if (numericOption >= 1 && numericOption <= numberOfOptions)
				{
					options.get(numericOption).click();
				}
				else
				{
					System.out.println("\tIntroduzca una opcion valida.");
				}
			}
			// If the menu has an associated action (ie, it's not a static menu),
			// we clear the elements that compose it after each run,
			// in case the operation has generated new ones, or removed others.
			if (action != null)	options.clear();
		} while (isExitKey(option) == false);
		keyboard.close();
	}
	
	private void printMenu()
	{
		System.out.println(name);
		System.out.println("---------------------------------------------");
		int optionIndex = 1;
		for (UIElement listedOption : options)
		{
			System.out.println(optionIndex + ". " + listedOption.getName());
		}
		System.out.println(exitKey + ". " + exitText);
		System.out.println("---------------------------------------------");
        System.out.print("Introduzca una opcion: ");
	}
	
	private boolean isExitKey(String option)
	{
		return option.equalsIgnoreCase(String.valueOf(exitKey));
	}
	
	public String getName() {
		return name;
	}
	
	public void addOption(UIElement newOption)
	{
		options.add(newOption);
		numberOfOptions++;
	}

}
