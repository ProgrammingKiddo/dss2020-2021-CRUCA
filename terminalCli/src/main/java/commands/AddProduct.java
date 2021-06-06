package commands;

/**
 * 
 * @author Borja_FM
 *
 */

import coreapi.Product;
import terminalcli.Screen;
import coreapi.InsufficientStockException;

public class AddProduct implements Command {

	private Product prod;
	
	public AddProduct(Product prod)
	{
		this.prod = prod;
	}
	
	public void execute()
	{
		try {
			Screen.ordSer.addProductToOrder(Screen.activeCafeteria, Screen.activeOrder, prod, 1);			
		} catch (InsufficientStockException ex)
		{
			System.err.println(ex.toString());
		}
	}
	
}
