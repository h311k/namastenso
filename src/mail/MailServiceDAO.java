package mail;

import javax.persistence.EntityManager;

import conexao.FabricaConexao;

public class MailServiceDAO {

	public void salvaMailService(int idUsuario, String nome, String host, String porta, String usuario, String senha ) {
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		MailService ms = new MailService();
		ms.setIdUsuario(idUsuario);
		ms.setNome(nome);
		ms.setHost(host);
		ms.setPorta(porta);
		ms.setUsuario(usuario);
		ms.setSenha(senha);
		manager.getTransaction().begin();
		manager.persist(ms);
		manager.close();
	}
}
