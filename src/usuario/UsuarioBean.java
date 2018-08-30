package usuario;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	private int idUsuario;
	private String email;
	private String dataInscricao;
	private boolean ativo;

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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@SuppressWarnings("deprecation")
	public void efetuaLogin() {
		Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String usuario = requestParamMap.get("user");
		String pass = requestParamMap.get("pass");
		UsuarioDAO ud = new UsuarioDAO();
		Usuario u = ud.validaLogin(usuario, pass);
		RequestContext requestContext = RequestContext.getCurrentInstance();
		if (u == null) {
			requestContext.addCallbackParam("retorno", "falha");
		} else {
			idUsuario = u.getIdUsuario();
			email = u.getEmail();
			dataInscricao = u.getDataInscricao().format(DateTimeFormatter.ofPattern("dd/MM/dd HH:mm:ss"));
			ativo = u.isAtivo();
			requestContext.addCallbackParam("retorno", "ok");
			requestContext.getAttributes().put("idUsuario", idUsuario);
			// inserir condicao caso o usuario nao esteja ativo e redirecionar para a futura
			// pagina de cadastro.
			try {
				if (ativo) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				} else {
					requestContext.addCallbackParam("retorno", "desativado");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void validaUsuario() {
		if (this.getEmail() == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void validaEmail() {
		Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String email = requestParamMap.get("email");
		UsuarioDAO ud = new UsuarioDAO();
		boolean existente = ud.validaEmail(email);
		if (existente) {
			requestContext.addCallbackParam("retorno", "existente");
		} else {
			requestContext.addCallbackParam("retorno", "inexistente");
		}
	}

	@SuppressWarnings("deprecation")
	public void criaUsuario() {
		Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String email = requestParamMap.get("email");
		String senha = requestParamMap.get("senha");
		UsuarioDAO ud = new UsuarioDAO();
		ud.criaConta(email, senha);
		try {
			requestContext.addCallbackParam("retorno", "ok");
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			requestContext.addCallbackParam("retorno", "erro");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void ativaUsuario() {
		UsuarioDAO ud = new UsuarioDAO();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		try {
			if (this.getIdUsuario() != 0 && ud.ativaUsuario(this.getIdUsuario())) {
				requestContext.addCallbackParam("retorno", "existente");
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} else {
				requestContext.addCallbackParam("retorno", "existente");
				FacesContext.getCurrentInstance().getExternalContext().redirect("criar-usuario.xhtml");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
