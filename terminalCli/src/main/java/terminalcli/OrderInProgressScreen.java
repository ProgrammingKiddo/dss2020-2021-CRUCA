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
public class OrderInProgressScreen
{
	private Scanner keyboard;
	private OrderService ordSer;
	
	OrderInProgressScreen()
	{
		keyboard = new Scanner(System.in);
		ordSer = new OrderService();
	}
	
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
}