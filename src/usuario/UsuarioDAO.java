package usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import conexao.FabricaConexao;
import mail.ServicoEmailController;
import util.DadosServidor;

public class UsuarioDAO {

	/**
	 * 
	 * Valida o login de acesso ou retorna mensagem de erro caso contrario.
	 * 
	 * @param user - Usuario digitado no form de login
	 * @param pass - Password digitado no form de login
	 */
	protected Usuario validaLogin(String user, String pass) {
		pass=encriptaSenha(pass);
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		Query query = manager.createNamedQuery("AUTENTICA_USUARIO", Usuario.class);
		query.setParameter("email", user);
		query.setParameter("senha", pass);
		Usuario usuario;
		try {
			usuario = (Usuario) query.getSingleResult();
		} catch(NoResultException e) {
			usuario=null;
		}	
		return usuario;	
	}
	
	protected boolean validaEmail(String email) {
		boolean existente;
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		Query query = manager.createNamedQuery("BUSCA_USUARIO_POR_EMAIL", Usuario.class);
		query.setParameter("email", email);
		try {
			query.getSingleResult();
			existente=true;
		} catch(NoResultException e) {
			existente=false;
		}
		return existente;
	}
	
	/**
	 * 
	 * Cria a conta desativada de usuario e envia email para ativacao
	 */
	protected void criaConta(String user, String pass) {
		LocalDateTime now = LocalDateTime.now();  
		pass=encriptaSenha(pass);
		Usuario usuario = new Usuario();
		usuario.setEmail(user);
		usuario.setSenha(pass);
		usuario.setAtivo(false);
		usuario.setDataInscricao(now);
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
		manager.close();	
		enviaEmailAtivacao(usuario);
	}
	/**
	 * Ativa usuario para a criacao de perfil
	 * 
	 * @param id - ID do usuario passado por parametro na URL do email de ativacao
	 * 
	 * */
	protected boolean ativaUsuario(int id) {
		boolean retorno;
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		Usuario usuario = manager.find(Usuario.class, id);
		try {
			usuario.setAtivo(true);
			manager.getTransaction().begin();
			manager.merge(usuario);
			manager.getTransaction().commit();
			manager.close();
			retorno=true;
		} catch(NoResultException e) {
			retorno=false;
		}
		return retorno;
	}
	
	/**
	 *
	 * Envia e-mail de ativacao para o usuario no momento da criacao da conta
	 * @param usuario - email do usuario criado
	 */
	private void enviaEmailAtivacao(Usuario usuario) {
		ServicoEmailController sec = new ServicoEmailController();
		//Utiliza a conta de email do admin para enviar email de ativacao
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		Query query = manager.createNamedQuery("BUSCA_USUARIO_POR_EMAIL", Usuario.class);
		query.setParameter("email", usuario.getEmail());
		try {
			usuario = (Usuario)query.getSingleResult();
		} catch(NoResultException e) {
			e.printStackTrace();
		}
		String url = DadosServidor.getUrlBase();
		url+="/ativar-conta.xhtml?idUsuario="+usuario.getIdUsuario();
		String mensagem = 
				"<h3>Olá.</h3>"
				+"<br/><br/>"
				+"<p>Obrigado por se cadastrar no Namastenso.</p>"
				+"<br/>"
				+"<p>Para ativar sua conta e criar seu perfil, <a href="+url+">clique aqui<a></p>";
		sec.enviaEmail(1, "Webmaster@namastenso.com", usuario.getEmail(), "Ative sua conta", mensagem);
	}
	
	
	/**
	 * Converte o valor inserido para chave MD5.
	 * 
	 * @param pass (Password antes da encriptacao).
	 * @return Password encriptado.
	 * 
	 */
	private String encriptaSenha(String pass) {
		MessageDigest md=null;
		String retorno="";
		try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		md.update(pass.getBytes());
        byte[] hashMd5 = md.digest();
        for(int i=0;i<hashMd5.length;i++){
            retorno+=hashMd5[i];
        }
        retorno = retorno.replaceAll("-", "");
		return retorno;
	}
}
