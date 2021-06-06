package terminalcli;

import coreapi.Cafeteria;
import coreapi.InvalidDateException;
import coreapi.OrderService;

import java.util.Scanner;
import java.time.LocalDate;
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
	
	public DailyRegisterScreen(OrderService ordSer, Scanner keyboard)
	{
		this.keyboard = keyboard;
		this.ordSer = ordSer;
	}
	
	public void showScreen(Cafeteria coffee, LocalDate date)
	{
	    char option;
	    int numberOfOrders = 0;
	    BigDecimal daily = BigDecimal.ZERO;
	    try {
	    	numberOfOrders = ordSer.getNumberOfDailyOrders(coffee, date);	    	
	    	daily = ordSer.getTotalDailyRegister(coffee, date);
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
	        option = keyboard.next().toCharArray()[0];
	        
	        if(option != 'r' && option != 'R')
	        {
	            System.out.println("\tIntroduzca una opcion valida.");
	        }
	    } while (option != 'r' && option != 'R');
	}
	
}