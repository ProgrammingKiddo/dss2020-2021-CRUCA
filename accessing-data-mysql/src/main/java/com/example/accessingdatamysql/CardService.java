package com.example.accessingdatamysql;

/**
 * Contains the implementation of the functions used by the controller.
 * @author Fran
 * @author Maria
 * 
 */
import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coreapi.*;
import data.*;
import apihttp.*;


@Service
public class CardService {

	@Autowired
    private OrderService OService;
	@Autowired
    private UserData DU;
	@Autowired
    private OrderData DO;
	@Autowired
    private CardData DC;
	@Autowired
    private MailService MS;
	@Autowired
    private PaymentRepository PR;
	@Autowired
    private ReloadRepository RR;
    
    public CardService(PaymentRepository pr, ReloadRepository rr,OrderData dord,UserData du, MailService ml, OrderService os, CardData cd)
    {
        this.DU = du;
        this.DO = dord;
        this.MS = ml;
        this.DC = cd;
        this.OService = os;
        this.PR = pr;
        this.RR = rr;
    }
    
    /**
     * Returns the amount of balance that the user has on his card.
     * @param uDNI	ID of the user whose card balance we are going to verify.
     * @param c		User card.
     * @return		Returns the amount of the user's balance on the card.
     * @throws WrongTransactionException	If the operation to be performed 
	 * 										fails because the data entered is incorrect.
     */
    public BigDecimal userBalace(int uDNI, Card c) throws WrongTransactionException
    {
        try {
            if(uDNI == c.getUserDni())
            {
                return c.getBalance();
            }
            else
            {
                throw new WrongTransactionException("User DNI and card user DNI are different");
            }
        }catch(WrongTransactionException ex)
        {
            return new BigDecimal(0);
        }
    }
    
    /**
     * Carry out a reload on a user's card.
     * @param r		Reload data.
     * @throws WrongTransactionException	If the operation to be performed 
	 * 										fails because the data entered is incorrect.
     */
    public void addBalance(Reload r) throws WrongTransactionException
    {
        try
        {
            Card c = DC.getCard(r.getNCard());
            c.addBalance(r.getNCard(), r.getQuantity());
            r = RR.save(r);
        }catch(WrongTransactionException ex)
        {
            
        }
        
    }
    
    /**
     * Send an email to the user with the validation code 
     * to make the payment of a specific order.
     * @param o		The specific order to pay.
     * @param uDNI	The ID of the user who is going to pay for the order.
     */
    public void paymentAuthoritation(Order o, int uDNI)
    {
        User u = DU.getUser(uDNI);
        char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ".toCharArray();
        int charsLength = chars.length;
        Random r = new Random();
        StringBuffer b = new StringBuffer();
        for(int i = 0; i < 12; i++)
        {
            b.append(chars[r.nextInt(charsLength)]);
        }
        OService.setCode(b.toString(),o);
        MS.sendEmail(u.getEmail(), "Validation Code", "The code to validate the order is: " + b.toString());
        DO.saveOrder(o);
    }
    
    /**
     * Register the payment of an order with a specific card.
     * @param p			Payment data.
     * @param nCard		The number of the card with which we are going to make the payment.
     * @throws WrongTransactionException	If the operation to be performed 
	 * 										fails because the data entered is incorrect.
     */
    public void PayRegister(Payment p, int nCard) throws WrongTransactionException
    {
        try
        {
            Card c = DC.getCard(nCard);
            c.deleteBalance(p.getUser().getDni(),nCard,p.getBalance());
            p = PR.save(p);
            DC.saveCard(c);
        }catch(WrongTransactionException e)
        {
            
        }
    }
    
    /**
     * Check that the payment has been made correctly and the order status has been changed to <code>PAYED</code>.
     * @param idord		Contains the id of the Order to obtain his code.
     * @param iduser	Contains the user id.
     * @param code		The code introduced by the user.
     * @throws UnreachableStatusException	If the conditions to set the <code>PAYED</code> status aren't met.
     * @throws WrongTransactionException	If the operation to be performed 
	 * 										fails because the data entered is incorrect.
     */
    public void payconfirm(int idord, int iduser, String code) throws WrongTransactionException,UnreachableStatusException
    {
        User u = DU.getUser(iduser);
        Order o = DO.getOrder(idord);
        Card c = DC.getCard(u.getNcard());
        if(o.getCode().equals(code))
        {
        	try
        	{
        		OService.OrderStatus_Payed(o);
        		PayRegister(new Payment("Pago Pedido " + idord, u , o.totalCost()), c.getCardNumber());
        		
        	}
        	catch(UnreachableStatusException ex)
        	{
        		
        	}
        	catch(WrongTransactionException ex)
        	{
        		
        	}
            
        }
    }
  
}