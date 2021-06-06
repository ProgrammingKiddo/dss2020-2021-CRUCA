package apihttp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Class representing the mail service used for our application
 * @author María
 * 
 */

@Service
public class MailService 
{
	// We do the JavaMailSender dependency injection
	@Autowired
    private JavaMailSender mailSender;
	
	/**
	 * Send an email to an concrete address
	 * @param to			contains a destination address
	 * @param subject		contains the subject of the message
	 * @param content		contains the body of the message
	 */
    public void sendEmail(String to, String subject, String content) 
    {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(to);
        email.setSubject(subject);
        email.setText(content);

        mailSender.send(email);
    }

}
