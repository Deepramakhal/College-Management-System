package util;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
public class EmailUtil {
    private static final String SMTP_HOST = "smtp.gmail.com"; 
    private static final String SMTP_PORT = "587"; 
    private static final String SMTP_USERNAME = "in.technovainstitute@gmail.com"; 
    private static final String SMTP_PASSWORD = "omdc houi xhli pees";  
    public static void sendEmail(String toEmail, String subject, String messageContent) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true"); 
        properties.put("mail.smtp.starttls.enable", "true"); 
        properties.put("mail.smtp.host", SMTP_HOST); 
        properties.put("mail.smtp.port", SMTP_PORT); 
        Session session = Session.getInstance(properties, new Authenticator() { //Authentication
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD); 
                }});
        Message message = new MimeMessage(session); 
        message.setFrom(new InternetAddress(SMTP_USERNAME)); 
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); 
        message.setSubject(subject); 
        message.setText(messageContent); 
        Transport.send(message); 
        System.out.println("Email sent successfully to " + toEmail);
    }
}
