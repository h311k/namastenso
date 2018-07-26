package mail;

import javax.persistence.EntityManager;

import conexao.FabricaConexao;
import security.Security;

public class MailServiceDAO {

	public void addUpdateMailService(int idUsuario, String nome, String host, String porta, String usuario, String senha) {
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		Security sec = new Security();
		senha=sec.encode(senha);
		manager.getTransaction().begin();
		MailService ms = new MailService(idUsuario, nome, host, porta, usuario, senha);
		manager.merge(ms);
		manager.getTransaction().commit();
		manager.close();
	}

	public void removeMailService(int idUsuario) {
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();

		manager.getTransaction().begin();
		MailService ms = manager.find(MailService.class, idUsuario);
		manager.getTransaction().commit();

		manager.getTransaction().begin();
		if(ms!=null) {
			manager.remove(ms);
		}
		manager.getTransaction().commit();
		manager.close();
	}
}
