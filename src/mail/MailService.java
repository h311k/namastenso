package mail;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mailService")
public class MailService {

	@Id
	private int idUsuario;
	private String nome;
	private String host;
	private String porta;
	private String usuario;
	private String senha;
	
	public MailService(int idUsuario, String nome, String host, String porta, String usuario, String senha) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.host = host;
		this.porta = porta;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public MailService() {
		
	}

	protected int getIdUsuario() {
		return idUsuario;
	}

	protected void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	protected String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected String getHost() {
		return host;
	}

	protected void setHost(String host) {
		this.host = host;
	}

	protected String getPorta() {
		return porta;
	}

	protected void setPorta(String porta) {
		this.porta = porta;
	}

	protected String getUsuario() {
		return usuario;
	}

	protected void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	protected String getSenha() {
		return senha;
	}

	protected void setSenha(String senha) {
		this.senha = senha;
	}
	
}
