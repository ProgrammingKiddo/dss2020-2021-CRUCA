package terminalcli;
import coreapi;
import api.src.main.java.coreapi.*;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.lang.*;

public class Screen
{
	/*--------------------------------------- MAIN_SCREEN --------------------------------------------- */
	
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
            System.out.println("Introduzca una opcion:");
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
                    	
                    	Order ord = OrderFactory.createOrder(coffe);
                        OrderInProgress_screen(coffe,ord);
                        break;
                    
                    case 2: 
                    	
                    	int d,m,y;
                    	Scanner keyboard = new Scanner(System.in);
                    	System.out.println("Introduce la fecha de la caja del dia que quiere consultar (dia, mes y año):");
                    	d = keyboard.nextInt();
                    	m = keyboard.nextInt();
                    	y = keyboard.nextInt();
                    	LocalDate date = LocalDate.of(y,m,d);
                    	
                    	DailyRegister_screen(coffe, date);
                         break;
                         
                    default:
                        System.out.println("Introduzca una opcion valida.");
                }
            }
            
        }while(s != 1 || s != 2); 
    }
	
	/*-------------------------------------- ORDER_IN_PROGRESS_SCREEN --------------------------------------*/
	
	public void OrderInProgress_screen(Cafeteria coffe, Order ord)
    {
        String op;
        int s = 0;
        Scanner keyboard = new Scanner(System.in);
        
        do
        {
            System.out.println("Pedido en curso (" + ord.getOrderCount() + ")");
            System.out.println("---------------------------------------------");
            System.out.println("1. Añadir producto"); 
            System.out.println("2. Eliminar producto");
            System.out.println("3. Finalizar pedido");
            System.out.println("----");
            System.out.println("R. Volver a la pantalla anterior");
            System.out.println("---------------------------------------------");
            System.out.println("Introduzca una opcion:");
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
	                    type_screen(coffe,ord);
	                    break;
	                
	                case 2: 
	                	 Remove_Product_screen(coffe,ord);
	                     break;
	                
	                case 3:
	                	 Finish_Order_screen(coffe,ord);
	                     break;
	                     
	                default:
	                    System.out.println("Introduzca una opcion valida.");
                }
            }
        }while(s != 1 || s != 2 || op != 3);
    }
	
	/* ------------------------------SELECT_TYPE_SCREEN ------------------------------------- */
	
	public void type_screen(Cafeteria coffe, Order ord)//Terminar
	{
		String op;
	    int s = 0, range = 0;
	    Scanner keyboard = new Scanner(System.in);
	    List<String> typeProduct = coffe.getTypes();
	    do
	    {
	        System.out.println("Tipo de productos");
	        System.out.println("---------------------------------------------");
	        for(String type: typeProduct)
	        {
	            range = range + 1;
	            System.out.println(range + '.' + type);
	        }
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.println("Introduzca una opcion");
	        op = keyboard.nextString();
	        if(op.compareTo("R"))
	        {
	        	OrderInProgress_screen(coffe);
	        }
	        else
	        {
	        	s = Interger.parseInt(op);
	        	if(s < 1 || s > range)
	        		System.out.println("Introduzca una opcion valida");
	        	else
		        {
		            
		        }
	        }
	    }while(s < 1 || s > range);
	}
	
	/*-------------------------------- SELECT_PRODUCT_SCREEN ------------------------------------ */
	
	public void product_screen(Cafeteria coffe, Order ord, String type)//Terminar
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
	        System.out.println("Introduzca una opcion");
	        op = keyboard.nextString();
	        if(op.compareTo("R"))
	        {
	        	type_screen(coffe);
	        }
	        else
	        {
	        	s = Interger.parseInt(op);
	        	if(s < 1 || s > range)
	        		System.out.println("Introduzca una opcion valida");
	        	else
		        {
		            //�Funciones para un producto?
		        }
	        }
	    }while(s < 1 || s > range);
	}
	
	/*------------------------------------ DELETE_PRODUCT_SCREEN----------------------------------------*/
	
	public void Remove_Product_screen(Cafeteria coffe, Order ord)
	{
		int s = 0,cont = 0, q = 0;
	    String op, range = 0;
	    Scanner keyboard = new Scanner(System.in);
	    Map<Product,int> basket = ord.getBasket();
	    Iterator it = basket.keySet().iterator();
	    do
	    {
	        System.out.println("Eliminar producto del pedido");
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
	        System.out.println("Introduzca una opcion");
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
	        		System.out.println("Introduzca una opcion valida");
	        	}
	        	else
	        	{
	        		System.out.println("Introduce la cantidad de producto a eliminar:");
	        		q = kayboard.nextInt();
	        		while(cont < s && it.hasNext())
	        		{
	        			cont++;
	        		}
	        		OrderService.removeProductFromOrder(ord, it.next().getId(), q);
	        	}
	        }
	    }while(op < 1 || op > range);
	}
	
	/*----------------------------------- FINISH_ORDER_SCREEM ----------------------------------- */
	
	public void Finish_Order_screen(Cafeteria coffe, Order ord)
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
	        System.out.println("Introduzca una opcion");
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
	        		OrderService.OrderStatus_Payed(ord);
	        		OrderService.OrderStatus_Finished(ord);
	        		main_screen(coffe);
	        	}
	        	else
	        	{
	        		System.out.println("Introduzca una opcion valida");
	        	}
	        }
	        
	    }while(s != 1);
	}
	
	/*------------------------------ DAILY_REGISTER_SCREEN--------------------------------- */
	
	public void DailyRegister_screen(Cafeteria coffe, LocalDate date)
	{
	    char op;
	    int n_orders = OrderService.getNumberOfOrdersDailyRegister(coffe, date);
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
	        System.out.println("Introduzca una opcion");
	        op = keyboard.nextChar();
	        
	        if(op != 'R')
	        {
	            System.out.println("Introduzca una opcion valida");
	        }
	        else
	        {
	        	main_screen(coffe);
	        }
	        
	    }while(op != 'R');
	}
	
}