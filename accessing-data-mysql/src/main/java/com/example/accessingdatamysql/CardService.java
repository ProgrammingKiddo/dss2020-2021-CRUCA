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
     * @return		Returns the amount of the user's balance on the card.
     */
    public BigDecimal userBalace(int uDNI) 
    {
       return DC.getCard(DU.getUser(uDNI).getNcard()).getBalance();
    }
    
    /**
     * Carry out a reload on a user's card.
     * @param r		Reload data.
     */
    public void addBalance(Reload r)
    {
        try
        {
            Card c = DC.getCard(r.getNCard());
            c.addBalance(r.getNCard(), r.getQuantity());
            r = RR.save(r);
            DC.saveCard(c);
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
    public void paymentAuthoritation(int idord, int uDNI)
    {
        User u = DU.getUser(uDNI);
        Order o = DO.getOrder(idord);
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
     * Auxiliary method to test payment validation
     * @param idord		Order id to pay
     * @param cod		Validation code
     */
    public void setcode(int idord, String cod)
    {
    	Order o = DO.getOrder(idord);
    	OService.setCode(cod, o);
    	DO.saveOrder(o);
    	
    }
    
    /**
     * Register the payment of an order with a specific card.
     * @param p			Payment data.
     * @param nCard		The number of the card with which we are going to make the payment.
     */
    public void payRegister(Payment p, int nCard)
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
     */
    public void payconfirm(int idord, int iduser, String code)
    {
        User u = DU.getUser(iduser);
        Order o = DO.getOrder(idord);
        Card c = DC.getCard(u.getNcard());
        if(o.getCode().equals(code))
        {
        	try
        	{
        		OService.OrderStatus_Payed(o);
        		payRegister(new Payment("Pago Pedido " + idord, u , o.totalCost()), c.getCardNumber());
        	}
        	catch(UnreachableStatusException ex)
        	{
        		
        	}
            
        }
    }
    
    public Card vercard(int iduser)
    {
    	return DC.getCard(DU.getUser(iduser).getNcard());
    }
  
}