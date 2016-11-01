package tunisia.mall.GUI;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleMail 
{
public static void envoyerMail(String to, String from, String msg, String sujet) throws Exception 
{
Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider()); 
Properties props = new Properties();
props.setProperty("mail.transport.protocol", "smtp"); 
props.setProperty("mail.host", "smtp.gmail.com");

props.put("mail.smtp.auth", "true");
props.put("mail.smtp.port", "465");

props.put("mail.debug", "true");
props.put("mail.smtp.socketFactory.port", "465");

props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
props.put("mail.smtp.socketFactory.fallback", "false");

Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() 
{ 
protected PasswordAuthentication getPasswordAuthentication() 
{
return new PasswordAuthentication("tunisiamalladm@gmail.com","tunisiamall2016");
}
});
session.setDebug(true); 
Transport transport = session.getTransport();
InternetAddress addressFrom = new InternetAddress("chamseddine.bensalem@esprit.tn"); 
MimeMessage message = new MimeMessage(session);
message.setSender(addressFrom);


message.setSubject(sujet);
message.setContent(msg, "text/plain");
String sendTo [] = {to};
if (sendTo != null) {
InternetAddress[] addressTo = new InternetAddress[sendTo.length];
for (int i = 0; i < sendTo.length; i++) {
addressTo[i] = new InternetAddress(sendTo[i]);
}
message.setRecipients(Message.RecipientType.TO, addressTo);

}
transport.connect();
transport.send(message);
transport.close();
System.out.println("DONE");
}
}
 
