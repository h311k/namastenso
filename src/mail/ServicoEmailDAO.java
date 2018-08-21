package mail;

import javax.persistence.EntityManager;

import conexao.FabricaConexao;
import security.Security;

public class ServicoEmailDAO {

	public void addUpdateMailService(int idUsuario, String nome, String host, String porta, String usuario, String senha) {
		Security sec = new Security();
		senha=sec.encode(senha);
		ServicoEmail ms = new ServicoEmail(idUsuario, nome, host, porta, usuario, senha);
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		manager.getTransaction().begin();
		manager.merge(ms);
		manager.getTransaction().commit();
		manager.close();
	}

	public void removeMailService(int idUsuario) {
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		ServicoEmail ms = manager.find(ServicoEmail.class, idUsuario);	
		if(ms!=null) {
			manager.getTransaction().begin();
			manager.remove(ms);
			manager.getTransaction().commit();
			manager.close();
		}	
	}
}
