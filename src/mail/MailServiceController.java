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
import javax.persistence.EntityManager;

import conexao.FabricaConexao;

public class MailServiceController {

	public void enviaEmail(int idUsuario, String remetente, String destinatario, String assunto, String mensagem) {
		
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		manager.getTransaction().begin();
		MailService ms = manager.find(MailService.class, idUsuario);
		manager.getTransaction().commit();
		manager.close();
		Properties props = new Properties();
		
		props.put("mail.smtp.host", ms.getHost());
		props.put("mail.smtp.socketFactory.port", ms.getPorta());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", ms.getPorta());
		

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ms.getUsuario(), ms.getSenha());
			}
		});
		
		Message message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(remetente));
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
