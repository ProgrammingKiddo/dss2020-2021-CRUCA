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
public class DailyRegisterScreen
{
	private Scanner keyboard;
	private OrderService ordSer;
	
	DailyRegisterScreen()
	{
		keyboard = new Scanner(System.in);
		ordSer = new OrderService();
	}
	
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