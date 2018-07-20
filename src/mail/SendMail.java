package mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public void EnviaEmail(String destinatario, String assunto, String mensagem, MailService mailService) {
		
		Properties props = new Properties();
		
		props.put("mail.smtp.host", mailService.getHost());
		props.put("mail.smtp.socketFactory.port", mailService.getPorta());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", mailService.getPorta());
		

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailService.getUsuario(), mailService.getSenha());
			}
		});
		
		Message message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(mailService.getRemetente()));
			Address[] toUser = InternetAddress.parse(destinatario);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assunto);
			message.setContent(mensagem,"text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
