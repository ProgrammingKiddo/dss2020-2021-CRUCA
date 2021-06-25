package com.example.accessingdatamysql;

/**
 * Class representing the functions which user can manage his payment and reload.
 * @author Fran
 * @author Maria
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coreapi.*;

@RestController
public class MainController 
{
	@Autowired
	private CardService CService;

	public MainController(CardService cs)
	{
		this.CService = cs; 
	}

	/**
	 * Returns the amount of balance that the user has on his card.
	 * @param dni	ID of the user whose card balance we are going to verify.
	 * @return		Returns the amount of the user's balance on the card.
	 */
	@GetMapping("/userbalance/{dni}")
	public Double getUserBalance(@PathVariable int dni)
	{
		return CService.userBalace(dni).doubleValue();
	
	}

	/**
	 * Carry out a reload on a user's card.
	 * @param R		Reload data.
	 */
	@PutMapping("/addbalance")
	public void addBalance(@RequestBody Reload R)
	{
			CService.addBalance(R);
	}

	/**
	 * Send an email to the user with the validation code 
     * to make the payment of a specific order.
	 * @param o		The specific order to pay.
	 * @param dni	The ID of the user who is going to pay for the order.
	 */
	@PutMapping("/payauthoritation/{dni}")
	public void payauthoritation(int idord, @PathVariable int dni)
	{
		CService.paymentAuthoritation(idord, dni);
	}
	
	/**
	 * Send an email to the user with the validation code 
     * to make the payment of a specific order.
	 * @param idord		Order id to pay
	 * @param code	Validation code
	 */
	@PutMapping("/setcode/{code}")
	public void setcode(int idord, @PathVariable String code)
	{
		CService.setcode(idord, code);
	}

	/**
	 * Register the payment of an order with a specific card.
	 * @param p			Payment data.
	 * @param ncard		The number of the card with which we are going to make the payment.
	 * @throws WrongTransactionException	If the operation to be performed 
	 * 										fails because the data entered is incorrect.
	 */
	@PutMapping("/payregister/{ncard}")
	public void payregister(@RequestBody Payment p, @PathVariable int ncard) throws WrongTransactionException
	{	
		CService.payRegister(p, ncard);	
	}

	/**
	 * Check that the payment has been made correctly and the order status has been changed to <code>PAYED</code>.
	 * @param ou		Map containing the ID of the order and the ID of the user to whom the order belongs.
	 * @param code		Validation code for the payment of the specific order
	 */
	@PutMapping("/payconfirm/{code}")
	public void payComfirm(int userId, int orderId, @PathVariable("code") String code)
	{
		CService.payconfirm(orderId, userId, code);
	}
	
	@GetMapping("/seecard/{iduser}")
	public Card seeCard(@PathVariable("iduser") int iduser)
	{
		return CService.vercard(iduser);
	}
}
