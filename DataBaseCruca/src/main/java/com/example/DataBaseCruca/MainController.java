package com.example.DataBaseCruca;
/**
 * 
 * @author Mar�a
 * @author Fran
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/gateway")
public class MainController {
@Autowired
	private PaymentRepository pr;
	private ReloadRepository rr;
}
