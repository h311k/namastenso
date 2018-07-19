package usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
		
		manager.getTransaction().begin();
		
		List<Usuario> usuario = query.getResultList();
		
		manager.getTransaction().commit();
		manager.close();
		if(!usuario.isEmpty()) {
			return usuario.get(0);
		} else {
			return null;
		}
		
	}
	
	/**
	 * 
	 * Cria a conta desativada de usuario e envia email para ativacao
	 */
	protected void criaConta(String user, String pass) {
		pass=encriptaSenha(pass);
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
