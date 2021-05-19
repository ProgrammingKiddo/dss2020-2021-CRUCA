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
public interface Screen
{	
	public void executeScreen();
	
	public void menuScreen();
	
	public void option();
	
}