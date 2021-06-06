package ui;

import commands.Command;

public class UIClick implements UIElement {

	private String name;
	private Command myCommand;
	
	UIClick(String name, Command assignedCommand)
	{
		this.name = name;
		myCommand = assignedCommand;
	}
	
	public String getName() {
		return name;
	}

	public void click() {
		// TODO Auto-generated method stub
		
	}

	
	
}
