package terminalcli;


import coreapi.Cafeteria;
import coreapi.Order;
import coreapi.OrderService;


/**
 * Implements all the functionality and interface for the user.
 * @author Maria
 * @author Fran
 * @author Borja
 * @version 0.1
 */
public class Screen
{	

	public static Cafeteria activeCafeteria;
	public static Order activeOrder;
	public static OrderService ordSer;
	public static String activeProductType;
	
	public Screen()
	{
		this.activeCafeteria = Terminal.coffee;
		this.ordSer = Terminal.ordSer; 
	}
	
	public void runCLI()
	{
		
	}
	
	public void createUI()
	{
		UIElement mainScreen = new UIMenu("Software de Cafeteria UCA", "Salir", 'Q');
		UIElement orderScreen = new UIMenu("Pedido en curso");
		UIElement coffeeTypesScreen = new UIMenu("Tipo de productos");
		UIElement coffeeProductsScreen = new UIMenu("Productos disponibles");
		UIElement orderRemoveScreen = new UIMenu("Eliminar producto del pedido");
		UIElement endOrderScreen = new UIMenu("Pedido en curso");
		UIElement checkRegisterScreen = new UIMenu("Consulta de la caja de hoy");
		
		
	}
}