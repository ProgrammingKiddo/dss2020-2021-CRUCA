package apihttp.src.main.java.apihttp;

import 

@Service
public class MailService 
{
	// We do the JavaMailSender dependency injection
	@Autowired
    private JavaMailSender mailSender;
	
    public void sendEmail(String to, String subject, String content) 
    {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(to);
        email.setSubject(subject);
        email.setText(content);

        mailSender.send(email);
    }
	
}
