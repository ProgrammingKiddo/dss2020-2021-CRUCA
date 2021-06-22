package com.example.accessingdatamysql;

import java.math.BigDecimal;
import java.util.Map;

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

	@GetMapping("/userbalance/{dni}")
	public BigDecimal getUserBalance(@RequestBody Card c, @PathVariable int dni)
	{
		return CService.userBalace(dni, c);
	}

	@PutMapping("/addbalance")
	public void addBalance(@RequestBody Reload R)
	{
		CService.addBalance(R);
	}

	@PutMapping("/payauthoritation/{dni}")
	public void payauthoritation(@RequestBody Order o, @PathVariable int dni)
	{
		CService.paymentAuthoritation(o, dni);
	}

	@PutMapping("/payregister/{ncard}")
	public void payregister(@RequestBody Payment p, @PathVariable int ncard)
	{
		CService.PayRegister(p, ncard);
	}

	@PutMapping("/payconfirm/{code}")
	public void payComfirm(@RequestBody Map<Integer, Integer> ou, @PathVariable("code") String code)
	{
		Map.Entry<Integer, Integer> entry = ou.entrySet().iterator().next();
	    CService.payconfirm(entry.getKey().intValue() , entry.getValue().intValue() , code);
	}
}
