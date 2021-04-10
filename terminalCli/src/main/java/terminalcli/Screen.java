package terminalcli;
import coreapi;
import java.io.*;

public class Screen
{
	/* Main Screen */
	
	public void main_screen(Cafeteria coffe)
    {
        String op;
        int s = 0;
        Scanner keyboard = new Scanner(System.in);
        do
        {
            System.out.println("Software de " + coffe.getName());
            System.out.println("---------------------------------------------");
            System.out.println("1. Crear pedido"); 
            System.out.println("2. Consultar caja de hoy");
            System.out.println("Q. Salir");
            System.out.println("---------------------------------------------");
            System.out.println("Introduzca una opción:”);
            op = keyboard.nextString();
            if(op.compareTo("Q"))
            {
            	System.exit();
            }
            else
            {
            	s = Interger.parseInt(op);
            	switch(s)
                {
                    case 1: 
                        createOrder(coffe);
                        //Llamar a interfaz de pedido en curso
                        break;
                    
                    case 2: 
                         //Llamar a interfaz de caja del dia
                         break;
                         
                    default:
                        System.out.println("Introduzca una opción válida");
                }
            }
        }while(s != 1 || s != 2); 
    }
	
	/* Order in Progress Screen */
	
	public void OrderInProgress_screen(Cafeteria coffe)
    {
        String op;
        int s = 0;
        Scanner keyboard = new Scanner(System.in);
        Order ord = createOrder(coffe);
        do
        {
            System.out.println("Pedido en curso (" + ord.getOrderCount() + ")";
            System.out.println("---------------------------------------------");
            System.out.println("1. Añadir producto"); 
            System.out.println("2. Eliminar producto");
            System.out.println("3. Finalizar pedido");
            System.out.println("----");
            System.out.println("R. Volver a la pantalla anterior");
            System.out.println("---------------------------------------------");
            System.out.println("Introduzca una opción:");
            op = keyboard.nextString();
            if(op.compareTo("R"))
            {
            	main_screen(coffe);
            }
            else
            {
            	s = Interger.parseInt(op);
            	switch(s)
                {
	                case 1: 
	                    //Llamar a interfaz de tipo de productos
	                    break;
	                
	                case 2: 
	                     //Llamar a interfaz de eliminar producto
	                     break;
	                
	                case 3:
	                     //Llamar interfaz finalizar pedido
	                     break;
	                     
	                default:
	                    System.out.println("Introduzca una opción válida");
                }
            }
        }while(s != 1 || s != 2 || op != 3);
    }
	
	/* Select Type Screen */
	
	public void type_screen(Cafeteria coffe)
	{
		String op;
	    int s = 0, range = 0;
	    Scanner keyboard = new Scanner(System.in);
	    //Crear lista de tipos en productCatalog?
	    List<String> typeProduct = coffe.getTypes();
	    do
	    {
	        System.out.println("Tipo de productos");
	        System.out.println("---------------------------------------------");
	        for(String s: typeProduct)
	        {
	            range = range + 1;
	            System.out.println(range + '.' + s);
	        }
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.println("Introduzca una opción");
	        op = keyboard.nextString();
	        if(op.compareTo("R"))
	        {
	        	OrderInProgress_screen(coffe);
	        }
	        else
	        {
	        	s = Interger.parseInt(op);
	        	if(s < 1 || s > range)
	        		System.out.println("Introduzca una opción válida");
	        	else
		        {
		            //Función Cafeteria que muestre los productos de ese tipo. Pondría product Screen y que
	        		// esta recibiese el tipo también.
		        }
	        }
	    }while(s < 1 || s > range);
	}
	
	/* Select Product Screen */
	
	public void product_screen(Cafeteria coffe)
	{
		String op;
		int s = 0, range = 0;
	    Scanner keyboard = new Scanner(System.in);
	    List<Product> AvailableProduct = coffe.getAvailableProducts();
	    do
	    {
	        System.out.println("Productos disponibles:");
	        System.out.println("---------------------------------------------");
	        for(Product p: AvailableProduct)
	        {
	            //Imprimir por id??
	            range = range + 1;
	            System.out.println(range + '.' + p.getName() + " (" + p.getPrice() + " euros)");
	        }
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.println("Introduzca una opción");
	        op = keyboard.nextString();
	        if(op.compareTo("R"))
	        {
	        	type_screen(coffe);
	        }
	        else
	        {
	        	s = Interger.parseInt(op);
	        	if(s < 1 || s > range)
	        		System.out.println("Introduzca una opción válida");
	        	else
		        {
		            //¿Funciones para un producto?
		        }
	        }
	    }while(s < 1 || s > range);
	}
	
	/* Delete Product Screen */
	
	public void Remove_Product_screen(Order ord) // FALTA
	{
		int s = 0;
	    String op, range = 0;
	    Scanner keyboard = new Scanner(System.in);
	    Map<Product,int> basket = ord.getBasket();
	    do
	    {
	        System.out.println(Eliminar producto del pedido);
	        System.out.println("---------------------------------------------");
	        for(Map.Entry<Product, Integer> entry : basket.entrySet())
	        {
	            range = range + 1;
	            if(entry.getValue().intValue() == 1)
	            {
	                if(entry.getKey().getPrice() == 1)
	                {
	                    System.out.println(range + ". " + entry.getKey().getName() + " (" + entry.getValue().intValue() + " ud x " + entry.getKey().getPrice() + " euro)");
	                }
	                else
	                {
	                    System.out.println(range + ". " + entry.getKey().getName() + " (" + entry.getValue().intValue() + " ud x " + entry.getKey().getPrice() + " euros)");
	                }
	            }
	            else
	            {
	                if(entry.getKey().getPrice() == 1)
	                {
	                    System.out.println(range + ". " + entry.getKey().getName() + " (" + entry.getValue().intValue() + " uds x " + entry.getKey().getPrice() + " euro)");
	                }
	                else
	                {
	                    System.out.println(range + ". " + entry.getKey().getName() + " (" + entry.getValue().intValue() + " uds x " + entry.getKey().getPrice() + " euros)");
	                }
	            }
	        }
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.println("Introduzca una opción");
	        op = kayboard.nextString();
	        if(op.compareTo("R"))
	        {
	        	OrderInProgress_screen(coffe);
	        }
	        else
	        {
	        	s = Interger.parseTo(op);
	        	if(op < 1 && op > range)
	        	{
	        		System.out.println("Introduzca una opción válida");
	        	}
	        	else
	        	{
	        		//Función para eliminar producto
	        	}
	        }
	    }while(op < 1 || op > range);
	}
	
	/* Finish Order Screen */
	
	public void Finish_Order_screen(Order ord)
	{
		int s = 0;
	    String op;
	    Scanner keyboard = new Scanner(System.in);
	    List<Product> AvailableProduct = coffe.getAvailableProducts();
	    do
	    {
	        System.out.println("Pedido en curso(" + ord.getId() + ')');
	        System.out.println("---------------------------------------------");
	        System.out.println("1. Pagar y finalizar pedido");
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.println("Introduzca una opción");
	        op = keyboard.nextString();
	        if(op.comparteTo("R"))
	        {
	        	OrderInProgress_screen(coffe);
	        }
	        else 
	        {
	        	s = Interger.parseTo(op);
	        	if(s == 1)
	        	{
	        		OrderService.OrderStatus_Finished(ord);
	        	}
	        	else
	        	{
	        		System.out.println("Introduzca una opción válida");
	        	}
	        }
	    }while(s < 1 || s > range);
	}
	
	/* Daily Register Screen */
	
	public void DailyRegister_screen(Cafeteria coffe, Date date)
	{
	    char op;
	    int n_orders = //Nº pedidos
	    BigDecimal daily = getDailyRegister(coffe,date);
	    Scanner keyboard = new Scanner(System.in);
	    do
	    {
	        System.out.println("Tipo de productos");
	        System.out.println("---------------------------------------------");
	        System.out.println("Pedidos registrados: " + n_orders);
	        System.out.println("Caja: " + daily);
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.println("Introduzca una opción");
	        op = keyboard.nextChar();
	        if(op != 'R')
	        {
	            System.out.println("Introduzca una opción válida");
	        }
	        else
	        {
	        	main_screen(coffe);
	        }
	    }while(op != 'R');
	}
	
}