package com.example.accessingdatamysql;
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
    
    public BigDecimal userBalace(int uDNI, Card c)
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
    
    public void addBalance(Reload r)
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
    
    public void PayRegister(Payment p, int nCard)
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
        		
        	}
        	catch(UnreachableStatusException ex)
        	{
        		
        	}
            PayRegister(new Payment("Pago Pedido " + idord, u , o.totalCost()), c.getCardNumber());
        }
    }
  
}