package mail;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
public class ServicoEmailBean {

	@ManagedProperty(value="#{usuarioBean.idUsuario}")
	private int idUsuario;
	private String nome;
	private String host;
	private String porta;
	private String usuario;
	private String senha;
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPorta() {
		return porta;
	}
	public void setPorta(String porta) {
		this.porta = porta;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@PostConstruct
	public void init() {
		ServicoEmailDAO sed = new ServicoEmailDAO();
		ServicoEmail se = sed.getServicoEmail(idUsuario);
		nome = se.getNome();
		host = se.getHost();
		porta = se.getPorta();
		usuario = se.getUsuario();
		senha = se.getSenha();
	}
	
	public void atualizaServicoEmail() {
		Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String nome = requestParamMap.get("nome");
		String host = requestParamMap.get("host");
		String porta = requestParamMap.get("porta");
		String usuario = requestParamMap.get("usuario");
		String senha = requestParamMap.get("senha");
		ServicoEmailDAO sed = new ServicoEmailDAO();
		sed.addUpdateMailService(idUsuario, nome, host, porta, usuario, senha);
	}
}
