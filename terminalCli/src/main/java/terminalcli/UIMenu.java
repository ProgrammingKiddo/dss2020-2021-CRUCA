package terminalcli;

import java.util.ArrayList;
import java.util.List;

public class UIMenu implements UIElement {

	private String name;
	private List<UIElement> options;
	private int numberOfOptions;
	
	public UIMenu(String name)
	{
		this.name = name;
		numberOfOptions = 0;
		options = new ArrayList<UIElement>();
	}
	
	public void click()
	{
		
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addOption(UIElement newOption)
	{
		options.add(newOption);
		numberOfOptions++;
	}

}
