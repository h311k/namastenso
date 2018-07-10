package usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.primefaces.context.RequestContext;

import conexao.FabricaConexao;
import conexao.Queries;

public class UsuarioDAO {

	/**
	 * 
	 * Valida o login de acesso ou retorna mensagem de erro caso contrário.
	 * 
	 * @param user - Usuário digitado no form de login
	 * @param pass - Password digitado no form de login
	 */
	protected Map<String,String> validaLogin(String user, String pass) {
		
		Map<String,String> dadosUsuario = new HashMap<String,String>();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		FabricaConexao fc = new FabricaConexao();
		Connection con = fc.getConexao();
		ResultSet rs = null;
		
		String mensagemRetorno = "Não achamos seu nome de usuário.";
		pass=encriptaSenha(pass);
		
		try {
            PreparedStatement ps = con.prepareStatement(Queries.AUTENTICA_USUARIO);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs=ps.executeQuery();
            
            if(rs.isBeforeFirst()) {
            	while(rs.next()) {
            		if(rs.getInt("ativo")==0) {
            			mensagemRetorno="Este usuário está inativado. Entre em contato conosco!";
            		} else {
            			if(!pass.equals(rs.getString("senha"))) {
                			mensagemRetorno = "Senha inválida.";
                		} else {
                			dadosUsuario.put("email", rs.getString("email"));
                			dadosUsuario.put("dataInscricao",df.format(rs.getDate("dataInscricao")));
                			mensagemRetorno = "ok";
                		}
            		} 
            	}
            }
            rs.close();
            
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		//Retorna mensagem de erro para tela ou "ok" para abrir a página principal
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.addCallbackParam("retorno", mensagemRetorno);
		return dadosUsuario;
	}
	/**
	 * Converte o valor inserido para chave MD5.
	 * 
	 * @param pass (Password antes da encriptação).
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
