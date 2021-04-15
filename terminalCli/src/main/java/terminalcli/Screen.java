package terminalcli;

import coreapi.Product;
import coreapi.ProductNotContainedInOrderException;
import coreapi.UnreachableStatusException;
import coreapi.Cafeteria;
import coreapi.InsufficientStockException;
import coreapi.Order;
import coreapi.OrderService;
import coreapi.OrderFactory;

import java.util.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;


/**
 * Implements all the functionality and interface for the user.
 * @author Maria
 * @author Fran
 * @author Borja
 * @version 0.1
 */
public class Screen
{
	private Scanner keyboard;
	private OrderService ordSer;
	
	Screen()
	{
		keyboard = new Scanner(System.in);
		ordSer = new OrderService();
	}
	/*--------------------------------------- MAIN_SCREEN --------------------------------------------- */
	
	public void main_screen(Cafeteria coffee)
    {
        char option;
        
        do
        {
            System.out.println("Software de " + coffee.getName());
            System.out.println("---------------------------------------------");
            System.out.println("1. Crear pedido."); 
            System.out.println("2. Consultar caja de hoy.");
            System.out.println("Q. Salir.");
            System.out.println("---------------------------------------------");
            System.out.print("Introduzca una opcion: ");
            option = keyboard.next().toCharArray()[0];
            
            switch(option)
            {
            	case '1':
                    OrderInProgress_Screen(coffee);
                    break;
                    
                case '2':    	
                	int day, month, year;
                	boolean correctDate;
                	LocalDate date = LocalDate.now();
                    System.out.println("Introduce la fecha de la caja del dia que quiere consultar (dia, mes, y año):");
                    
                    do
                    {
                    	day = keyboard.nextInt();
                    	month = keyboard.nextInt();
                    	year = keyboard.nextInt();
                    	try {
                    		date = LocalDate.of(year, month, day);
                    		correctDate = true;
                    	} catch (DateTimeException ex)
                    	{
                    		correctDate = false;
                    		System.out.println("Por favor, introduce una fecha valida (dia, mes, y año): ");
                    	}
                    } while (correctDate == false);
                    	
                    DailyRegisterScreen(coffee, date);
                    break;
                    
                default:
                        System.out.println("\tIntroduzca una opcion valida.");
            }
        } while (option != 'q' && option != 'Q');
        keyboard.close();
    }
	
	/*-------------------------------------- ORDER_IN_PROGRESS_SCREEN --------------------------------------*/
	
	public void OrderInProgress_Screen(Cafeteria coffee)
    {
		Order ord = OrderFactory.createOrder(coffee, LocalDateTime.now());
        char option;
        
        do
        {
            System.out.println("Pedido en curso (" + ord.getId() + ")");
            System.out.println("---------------------------------------------");
            System.out.println("1. Añadir producto."); 
            System.out.println("2. Eliminar producto.");
            System.out.println("3. Finalizar pedido.");
            System.out.println(".....");
            System.out.println("R. Volver a la pantalla anterior.");
            System.out.println("---------------------------------------------");
            System.out.println("Introduzca una opcion:");
            option = keyboard.next().toCharArray()[0];
            
            switch (option)
            {
            	case '1':
            		productTypeScreen(coffee, ord);
            		break;
            	
            	case '2':
            		removeProductScreen(coffee, ord);
                    break;
               
            	case '3':
               	 	if (finishOrderScreen(coffee, ord) == true)
               	 	{
               	 		option = 'R';
               	 	}
                    break;
            	case 'r':
            	case 'R':
            	   	break;
                    
            	default:
                   System.out.println("\tIntroduzca una opcion valida.");
            }

        } while (option != 'r' && option != 'R');
    }
	
	/* ------------------------------SELECT_TYPE_SCREEN ------------------------------------- */
	
	public void productTypeScreen(Cafeteria coffee, Order ord)
	{
		String option;
	    int counter;
	    int chosenType;
	    List<String> types;
	    
	    do
	    {
	    	// The list of types should be obtained right before each possible operation,
	    	// because it can change afterwards.
	    	types = coffee.getTypes();
	    	counter = 1;
	        System.out.println("Tipos de productos");
	        System.out.println("---------------------------------------------");
	        for (String type: types)
	        {
	            System.out.println(counter + ". " + type);
	            counter++;
	        }
	        System.out.println(".....");
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.print("Introduzca una opcion: ");
	        option = keyboard.nextLine();

	        if (!option.equalsIgnoreCase("R"))
	        {
	        	try
	        	{
	        		chosenType = Integer.parseInt(option);
	        		if (chosenType < 1 || chosenType > types.size())
	        		{
	        			throw new NumberFormatException();
	        		}
	        		
	        		productScreen(coffee, ord, types.get(chosenType-1));
	        	} catch (NumberFormatException ex)
	        	{
	        		System.out.println("\tIntroduzca una opcion valida.");
	        	}
	        }
	    } while (!option.equalsIgnoreCase("R"));
	}
	
	/*-------------------------------- SELECT_PRODUCT_SCREEN ------------------------------------ */
	
	public void productScreen(Cafeteria coffee, Order ord, String type)
	{
		String option;
		boolean correctChoice;
		int counter;
		int chosenProduct = 1;
		int productQuantity = 0;
	    List<Product> availableProducts;
	    
	    do
	    {
	    	availableProducts = coffee.getAvailableProducts();
	    	counter = 1;
	    	correctChoice = false;
	    	
	        System.out.println("Productos disponibles de la categoría \"" + type + "\"");
	        System.out.println("---------------------------------------------");
	        for(Product p: availableProducts)
	        {
	        	if (p.getType().equals(type))
	        	{
	        		System.out.println(counter + ". " + p.getName() + " (" + p.getPrice() + " euros)");
	        		counter++;	        		
	        	}
	        }
	        if (counter == 1)
	        {
	        	System.out.println("- No hay ningun producto disponible de ese tipo.");
	        }
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.println("Introduzca una opcion");
	        option = keyboard.nextLine();
	        
	        if (!option.equalsIgnoreCase("R"))
	        {
	        	// Get the product and the quantity to add
	        	try {
	        		chosenProduct = Integer.parseInt(option);
	        		if (chosenProduct < 1 || chosenProduct > availableProducts.size())
	        		{
	        			throw new NumberFormatException();
	        		}
	        		
	        		productQuantity = keyboard.nextInt();
	        		if (productQuantity < 1)
	        		{
	        			throw new InputMismatchException();
	        		}
	        		
	        		correctChoice = true;
	        	} catch (NumberFormatException ex)
	        	{
	        		System.out.println("\tIntroduzca una opcion valida.");
	        	}
	        	catch (InputMismatchException ex)
	        	{
	        		System.out.println("\tIntroduzca una cantidad de producto valida.");
	        	}
	        	// Add the amount of product to the order
	        	if (correctChoice == true)
	        	{
	        		try {
	        			ordSer.addProductToOrder(coffee, ord, availableProducts.get(chosenProduct-1).getId(), productQuantity);
	        		} catch (InsufficientStockException ex)
	        		{
	        			System.out.println("\tLo sentimos, pero solo tenemos " + coffee.getProductQuantity(availableProducts.get(chosenProduct-1)) + " unidades en stock.");
	        		}
	        	}
	        }

	    } while (!option.equalsIgnoreCase("R"));
	}
	
	/*------------------------------------ DELETE_PRODUCT_SCREEN----------------------------------------*/
	
	public void removeProductScreen(Cafeteria coffee, Order ord)
	{
	    String option;
	    String productString = "";
	    boolean correctChoice;
	    int chosenProduct =1, productQuantity =0;
	    int counter;
	    Map<Product, Integer> basket = ord.getBasket();
	    int productIds[] = new int[basket.size()];
	    do
	    {
	    	counter = 1;
	    	correctChoice = false;
	        System.out.println("Eliminar producto del pedido");
	        System.out.println("---------------------------------------------");
	        for(Map.Entry<Product, Integer> entry : basket.entrySet())
	        {
	        	productString = counter + ". " + entry.getKey().getName() + " (" + entry.getValue().intValue() + " ud";
	            if (entry.getValue().intValue() > 1)
	            {
	            	productString += "s";
	            }
	            productString += " x " + entry.getKey().getPrice().doubleValue() + " euro";
	            if (entry.getKey().getPrice().doubleValue() != 1.0)
	            {
	            	productString += "s";
	            }
	            productString += ")";
	            // We store the corresponding product Id to the given numerical choice position in the array
	            productIds[counter-1] = entry.getKey().getId();
	            counter++;
	        }
	        System.out.println(productString);
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.println("Introduzca una opcion: ");
	        option = keyboard.nextLine();
	        
	        if (!option.equalsIgnoreCase("R"))
	        {
	        	// Get the product and the quantity to remove
	        	try {
	        		chosenProduct = Integer.parseInt(option);
	        		if (chosenProduct < 1 || chosenProduct > basket.size())
	        		{
	        			throw new NumberFormatException();
	        		}
	        		
	        		productQuantity = keyboard.nextInt();
	        		if (productQuantity < 1)
	        		{
	        			throw new InputMismatchException();
	        		}
	        		
	        		correctChoice = true;
	        	} catch (NumberFormatException ex)
	        	{
	        		System.out.println("\tIntroduzca una opcion valida.");
	        	}
	        	catch (InputMismatchException ex)
	        	{
	        		System.out.println("\tIntroduzca una cantidad de producto a retirar valida.");
	        	}
	        	// Remove the amount of product to the order
	        	if (correctChoice == true)
	        	{
	        		int productId = productIds[chosenProduct-1];
	        		try {
	        			ordSer.removeProductFromOrder(ord, productId, productQuantity);
	        		} catch (InsufficientStockException ex)
	        		{
	        			System.out.println("\tSolo hay " + ord.checkProductQuantity(productId) + " unidade(s) de ese producto en el pedido .");
	        		}
	        		catch (ProductNotContainedInOrderException ex)
	        		{
	        			System.err.println(ex.getMessage());
	        		}
	        	}
	        }

	    } while (!option.equalsIgnoreCase("R"));
	}
	
	/*----------------------------------- FINISH_ORDER_SCREEM ----------------------------------- */
	
	public boolean finishOrderScreen(Cafeteria coffee, Order ord)
	{
	    char option;
	    boolean orderFinished = false;
	    
	    do
	    {
	        System.out.println("Pedido en curso (" + ord.getId() + ')');
	        System.out.println("---------------------------------------------");
	        System.out.println("Total a pagar: " + ord.totalCost().doubleValue() + " euros");
	        System.out.println("1. Pagar y finalizar pedido");
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.print("Introduzca una opcion: ");
	        option = keyboard.next().toCharArray()[0];
	        
	        if (option == '1')
	        {
	        	try {
	        		ordSer.OrderStatus_InKitchen(ord);
	        		ordSer.OrderStatus_Delivered(ord);
	        		ordSer.OrderStatus_Payed(ord);
	        		ordSer.OrderStatus_Finished(ord);
	        		System.out.println("+++Su pedido ha sido finalizado correctamente.\n+++Volviendo al menu principal...");
	        		orderFinished = true;
	        		option = 'R';
	        	} catch (UnreachableStatusException ex)
	        	{
	        		System.err.println(ex.getMessage());
	        	}
	        }
	        else if (option != 'r' && option != 'R')
	        {
	        	System.out.println("\tIntroduzca una opcion valida.");
	        }
	        
	    }while(option != 'r' && option != 'R');
	    
	    return orderFinished;
	}
	
	/*------------------------------ DAILY_REGISTER_SCREEN--------------------------------- */
	
	public void DailyRegisterScreen(Cafeteria coffee, LocalDate date)
	{
	    char option;
	    int numberOfOrders = ordSer.getNumberOfDailyOrders(coffee, date);
	    BigDecimal daily = ordSer.getTotalDailyRegister(coffee, date);
	    
	    do
	    {
	        System.out.println("Tipo de productos");
	        System.out.println("---------------------------------------------");
	        if (numberOfOrders == 0)
	        {
	        	System.out.println("No hay pedidos registrados para ese dia.");
	        }
	        else
	        {
	        	System.out.println("Pedidos registrados: " + numberOfOrders);
	        	System.out.println("Caja: " + daily.doubleValue() + "euros\n");	        	
	        }
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.print("Introduzca una opcion: ");
	        option = keyboard.next().toCharArray()[0];
	        
	        if(option != 'r' && option != 'R')
	        {
	            System.out.println("\tIntroduzca una opcion valida.");
	        }
	    } while (option != 'r' && option != 'R');
	}
	
}