package commands;

/**
 * 
 * @author Borja_FM
 *
 */

import coreapi.Product;
import coreapi.ProductNotContainedInOrderException;
import terminalcli.Screen;
import coreapi.InsufficientStockException;

public class RemoveProduct implements Command {

	private Product prod;
	
	public RemoveProduct(Product prod)
	{
		this.prod = prod;
	}
	
	public void execute()
	{
		try {
			Screen.ordSer.removeProductFromOrder(Screen.activeCafeteria, Screen.activeOrder, prod, 1);			
		} catch (InsufficientStockException ex)
		{
			System.err.println(ex.toString());
		} catch (ProductNotContainedInOrderException ex)
		{
			System.err.println(ex.toString());
		}
	}
	
}
