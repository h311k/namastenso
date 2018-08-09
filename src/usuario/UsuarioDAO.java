package usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import conexao.FabricaConexao;

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
		Query query = manager.createNamedQuery("VERIFICA_EXISTENCIA_USUARIO", Usuario.class);
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();  
		pass=encriptaSenha(pass);
		Usuario usuario = new Usuario();
		usuario.setEmail(user);
		usuario.setSenha(pass);
		usuario.setAtivo(false);
		usuario.setDataInscricao(sdf.format(now));
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
		manager.close();
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
