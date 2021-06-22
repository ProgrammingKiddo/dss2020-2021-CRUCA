package terminalcli;


import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import coreapi.Cafeteria;
import coreapi.InsufficientStockException;
import coreapi.InvalidDateException;
import coreapi.Order;
import coreapi.OrderFactory;
import coreapi.OrderService;
import coreapi.Product;
import coreapi.ProductNotContainedInOrderException;
import coreapi.UnreachableStatusException;


/**
 * Implements all the functionality and interface for the user.
 * @author Maria
 * @author Fran
 * @author Borja
 * @version 0.1
 */
public class Screen
{	

	public Cafeteria activeCoffee;
	public Order activeOrder;
	public OrderService ordSer;
	public String activeProductType;
	Scanner keyboard;
	
	public Screen(Cafeteria coffee, OrderService ordSer)
	{
		this.activeCoffee = coffee;
		this.ordSer = ordSer;
		keyboard = new Scanner(System.in);
	}
	
	
/*--------------------------------------- MAIN_SCREEN --------------------------------------------- */
	
	public void main_screen()
    {
        char option;
        
        do
        {
            System.out.println("Software de " + activeCoffee.getName());
            System.out.println("---------------------------------------------");
            System.out.println("1. Crear pedido."); 
            System.out.println("2. Consultar caja de hoy.");
            System.out.println("Q. Salir.");
            System.out.println("---------------------------------------------");
            System.out.print("Introduzca una opcion: ");
            
            option = readSingleCharacter();
           
            switch(option)
            {
            	case '1':
            		orderInProgress_Screen();
                    break;
                    
                case '2':    	
                	int day, month, year;
                	boolean correctDate;
                	LocalDate date = LocalDate.now();
                    System.out.println("Introduce la fecha de la caja del dia que quiere consultar.");
                    
                    do
                    {
                    	System.out.print("Dia: ");
                    	day = Integer.parseInt(keyboard.nextLine());
                    	System.out.print("Mes: ");
                    	month = Integer.parseInt(keyboard.nextLine());
                    	System.out.print("Anno: ");
                    	year = Integer.parseInt(keyboard.nextLine());
                    	try {
                    		date = LocalDate.of(year, month, day);
                    		correctDate = true;
                    	} catch (DateTimeException ex)
                    	{
                    		correctDate = false;
                    		System.out.print("Por favor, introduce una fecha valida (dia, mes, y año): ");
                    	}
                    } while (correctDate == false);
                    	
                    dailyRegisterScreen(date);
                    break;
                case 'q':
                case 'Q':
                	break;
                default:
                        System.out.println("\tIntroduzca una opcion valida.");
            }
        } while (option != 'q' && option != 'Q');
        keyboard.close();
    }
	
	/*-------------------------------------- ORDER_IN_PROGRESS_SCREEN --------------------------------------*/
	
	public void orderInProgress_Screen()
    {
		activeOrder = OrderFactory.createOrder(activeCoffee, LocalDateTime.now());
        char option;
        
        do
        {
            System.out.println("Pedido en curso (" + activeOrder.getId() + ")");
            System.out.println("---------------------------------------------");
            System.out.println("1. Añadir producto."); 
            System.out.println("2. Eliminar producto.");
            System.out.println("3. Finalizar pedido.");
            System.out.println(".....");
            System.out.println("R. Volver a la pantalla anterior.");
            System.out.println("---------------------------------------------");
            System.out.print("Introduzca una opcion: ");
            option = readSingleCharacter();
            
            switch (option)
            {
            	case '1':
            		productTypeScreen();
            		break;
            	
            	case '2':
            		removeProductScreen();
                    break;
               
            	case '3':
               	 	if (finishOrderScreen() == true)
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
	
	public void productTypeScreen()
	{
		String option;
	    int counter;
	    int chosenType;
	    List<String> types;
	    
	    do
	    {
	    	// The list of types should be obtained right before each possible operation,
	    	// because it can change afterwards.
	    	types = activeCoffee.getTypes();
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
	        		
	        		productScreen(types.get(chosenType-1));
	        	} catch (NumberFormatException ex)
	        	{
	        		System.out.println("\tIntroduzca una opcion valida.");
	        	}
	        }
	    } while (!option.equalsIgnoreCase("R"));
	}
	
	/*-------------------------------- SELECT_PRODUCT_SCREEN ------------------------------------ */
	
	public void productScreen(String type)
	{
		String option;
		boolean correctChoice;
		int counter;
		int chosenProduct = 1;
		int productQuantity = 0;
	    List<Product> availableProducts;
	    List<Product> productsOfChosenType = new ArrayList<Product>();
	    
	    do
	    {
	    	availableProducts = activeCoffee.getAvailableProducts();
	    	for (Product p : availableProducts)
	    	{
	    		if (p.getType().equals(type))
	    		{
	    			productsOfChosenType.add(p);
	    		}
	    	}
	    	counter = 1;
	    	correctChoice = false;
	    	
	        System.out.println("Productos disponibles de la categoría \"" + type + "\"");
	        System.out.println("---------------------------------------------");
	        for(Product p: productsOfChosenType)
	        {
	        	System.out.println(counter + ". " + p.getName() + " (" + p.getPrice().doubleValue() + " euros)");
	        	counter++;	        		
	        }
	        if (counter == 1)
	        {
	        	System.out.println("- No hay ningun producto disponible de ese tipo.");
	        }
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.print("Introduzca una opcion: ");

	        option = keyboard.nextLine();	        	

	        
	        if (!option.equalsIgnoreCase("R"))
	        {
	        	// Get the product and the quantity to add
	        	try {
	        		chosenProduct = Integer.parseInt(option);
	        		if (chosenProduct < 1 || chosenProduct > productsOfChosenType.size())
	        		{
	        			throw new NumberFormatException();
	        		}
	        		
	        		System.out.print("Introduzca una cantidad: ");
	        		productQuantity = Integer.parseInt(keyboard.nextLine());
	        		if (productQuantity < 1)
	        		{
	        			throw new NumberFormatException();
	        		}
	        		
	        		correctChoice = true;
	        	} catch (NumberFormatException ex)
	        	{
	        		System.out.println("\tIntroduzca una opcion valida.");
	        	}
	        	// Add the amount of product to the order
	        	if (correctChoice == true)
	        	{
	        		try {
	        			ordSer.addProductToOrder(activeCoffee, activeOrder, productsOfChosenType.get(chosenProduct-1), productQuantity);
	        		} catch (InsufficientStockException ex)
	        		{
	        			System.out.println("\tLo sentimos, pero solo tenemos " + activeCoffee.getProductQuantity(productsOfChosenType.get(chosenProduct-1)) + " unidades en stock.");
	        		}
	        	}
	        }
	        productsOfChosenType.clear();
	    } while (!option.equalsIgnoreCase("R"));
	}
	
	/*------------------------------------ DELETE_PRODUCT_SCREEN----------------------------------------*/
	
	public void removeProductScreen()
	{
	    String option;
	    String productString = "";
	    boolean correctChoice;
	    int chosenProduct =1, productQuantity =0;
	    int counter;
	    do
	    {
	    	Map<Product, Integer> basket = activeOrder.getBasket();
	    	Product products[] = new Product[basket.size()];
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
	            // We store the corresponding product to the given numerical choice position in the array
	            products[counter-1] = entry.getKey();
	            counter++;
	            System.out.println(productString);
	        }
	        
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.print("Introduzca una opcion: ");
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
	        		System.out.print("Introduzca una cantidad: ");
	        		productQuantity = Integer.parseInt(keyboard.nextLine());
	        		if (productQuantity < 1)
	        		{
	        			throw new NumberFormatException();
	        		}
	        		
	        		correctChoice = true;
	        	} catch (NumberFormatException ex)
	        	{
	        		System.out.println("\tIntroduzca una opcion valida.");
	        	}
	        	
	        	// Remove the amount of product to the order
	        	if (correctChoice == true)
	        	{
	        		Product prod = products[chosenProduct-1];
	        		try {
	        			ordSer.removeProductFromOrder(activeCoffee, activeOrder, prod, productQuantity);
	        		} catch (InsufficientStockException ex)
	        		{
	        			System.out.println("\tSolo hay " + activeOrder.checkProductQuantity(prod) + " unidade(s) de ese producto en el pedido .");
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
	
	public boolean finishOrderScreen()
	{
	    char option;
	    boolean orderFinished = false;
	    
	    do
	    {
	        System.out.println("Pedido en curso (" + activeOrder.getId() + ')');
	        System.out.println("---------------------------------------------");
	        System.out.println("Total a pagar: " + activeOrder.totalCost().doubleValue() + " euros");
	        System.out.println("1. Pagar y finalizar pedido");
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.print("Introduzca una opcion: ");
	        option = readSingleCharacter();
	        
	        if (option == '1')
	        {
	        	try {
	        		ordSer.OrderStatus_Payed(activeOrder);
	        		ordSer.OrderStatus_Finished(activeOrder);
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
	
	public void dailyRegisterScreen(LocalDate date)
	{
	    char option;
	    int numberOfOrders = 0;
	    BigDecimal daily = BigDecimal.ZERO;
	    
	    try {
	    	numberOfOrders = ordSer.getNumberOfDailyOrders(activeCoffee, date);	    	
	    	daily = ordSer.getTotalDailyRegister(activeCoffee, date);
	    } catch (InvalidDateException ex)
	    {
	    	System.out.println(ex.toString());
	    }
	    
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
	        option = readSingleCharacter();
	        
	        if(option != 'r' && option != 'R')
	        {
	            System.out.println("\tIntroduzca una opcion valida.");
	        }
	    } while (option != 'r' && option != 'R');
	}
	
	private char readSingleCharacter()
	{
		String input;
		char c = '\0';
		
		input = keyboard.nextLine();
		if (input.length() > 0)
		{
			c = input.charAt(0);
		}
		return c;
	}
	
}