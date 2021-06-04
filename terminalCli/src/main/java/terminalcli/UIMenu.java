package terminalcli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UIMenu implements UIElement {

	private String name;
	private List<UIElement> options;
	private int numberOfOptions;
	private char exitKey = 'R';
	private String exitText;
	
	public UIMenu(String name, String exitText)
	{
		this.name = name;
		this.exitText = exitText;
		numberOfOptions = 0;
		options = new ArrayList<UIElement>();
	}
	
	public UIMenu(String name, String exitText, char exitKey)
	{
		this(name, exitText);
		this.exitKey = exitKey;
	}
	
	public void click()
	{
		Scanner keyboard = new Scanner(System.in);
		String option;
		int numericOption;
		do
		{
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
