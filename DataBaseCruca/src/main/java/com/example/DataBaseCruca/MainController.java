package com.example.DataBaseCruca;
/**
 * 
 * @author Mar�a
 * @author Fran
 */
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(path = "/gateway")
public class MainController {
@Autowired
	private PaymentRepository pr;
	private ReloadRepository rr;
}
