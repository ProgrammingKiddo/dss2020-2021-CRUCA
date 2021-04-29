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
public class finishOrderScreen
{
	private Scanner keyboard;
	private OrderService ordSer;
	
	finishOrderScreen()
	{
		keyboard = new Scanner(System.in);
		ordSer = new OrderService();
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
}