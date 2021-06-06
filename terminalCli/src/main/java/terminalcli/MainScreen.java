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
public class MainScreen
{
	private Scanner keyboard;
	private OrderService ordSer;
	
	MainScreen(OrderService ordSer, Scanner keyboard)
	{
		this.keyboard = keyboard;
		this.ordSer = ordSer;
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
}	
