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
    private static final String SMTP_HOST = "smtp.gmail.com"; // Change as needed
    private static final String SMTP_PORT = "587"; // Common port for TLS
    private static final String SMTP_USERNAME = "makhaldeepra@gmail.com"; // Your email
    private static final String SMTP_PASSWORD = "bzsf tyek ckjv lcga"; // Your email password (use App Passwords if using Gmail)
    
    public static void sendEmail(String toEmail, String subject, String messageContent) throws MessagingException {
        Properties properties = new Properties(); // Set properties
        properties.put("mail.smtp.auth", "true"); // Enable SMTP authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
        properties.put("mail.smtp.host", SMTP_HOST); // SMTP host
        properties.put("mail.smtp.port", SMTP_PORT); // SMTP port
        

        Session session = Session.getInstance(properties, new Authenticator() { // Authenticate
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD); // Provide credentials
            }
        });

        Message message = new MimeMessage(session); // Compose email
        message.setFrom(new InternetAddress(SMTP_USERNAME)); // Set sender email
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); // Set recipient email
        message.setSubject(subject); // Set email subject
        message.setText(messageContent); // Set email body
       

        Transport.send(message); // Send email
        System.out.println("Email sent successfully to " + toEmail); // Log success
    }
}
