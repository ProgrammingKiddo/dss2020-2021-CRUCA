package commands;

/** 
 * @author Borja_FM
 */

import terminalcli.Screen;
import ui.UIElement;
import ui.UIMenu;

import java.util.List;

public abstract class ShowProductTypes implements Command {

	UIMenu menuObject;
	String productType;
	
	public ShowProductTypes(UIElement menu, String type)
	{
		menuObject = (UIMenu) menu;
		productType = type;
	}
	
	public void execute()
	{
		List<String> types = Screen.activeCafeteria.getTypes();
		
		for(String type : types)
		{
			UIElement newOption = new UIMenu("Productos disponibles");
			menuObject.addOption(newOption);
		}
	}
}
