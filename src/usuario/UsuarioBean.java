package usuario;

import java.io.IOException;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mail.MailServiceDAO;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	private int idUsuario;
	private String email;
	private String dataInscricao;

	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDataInscricao() {
		return dataInscricao;
	}


	public void setDataInscricao(String dataInscricao) {
		this.dataInscricao = dataInscricao;
	}


	public void efetuaLogin() {
		Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String usuario = requestParamMap.get("user");
		String pass = requestParamMap.get("pass");
		UsuarioDAO ud = new UsuarioDAO();
		Usuario u = ud.validaLogin(usuario, pass);
		RequestContext requestContext = RequestContext.getCurrentInstance();
		if(u==null) {
			requestContext.addCallbackParam("retorno", "Usuário ou senha inválidos");
		} else {
			idUsuario = u.getIdUsuario();
			email = u.getEmail();
			dataInscricao = u.getDataInscricao();
			requestContext.addCallbackParam("retorno", "ok");
			requestContext.getAttributes().put("idUsuario", idUsuario);
			//inserir condicao caso o usuario não esteja ativo e redirecionar para a futura pagina de cadastro.
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void validaUsuario() {
		if(this.getEmail()==null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
