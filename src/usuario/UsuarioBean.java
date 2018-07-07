package usuario;

import java.io.IOException;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UsuarioBean extends Usuario {


	public void efetuaLogin() {
		Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String usuario = requestParamMap.get("user");
		String pass = requestParamMap.get("pass");
		UsuarioDAO ud = new UsuarioDAO();
		Map<String,String> dadosUsuario = ud.validaLogin(usuario, pass);
		if(dadosUsuario!=null) {
			this.setEmail(dadosUsuario.get("email"));
			this.setDataInscricao("dataInscricao");
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
