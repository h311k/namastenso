package usuario;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author haony
 * 
 * Entidade de Usuario refletindo a tabela de usuarios do BD.
 *
 */
@Entity
@Table(name="usuarios")
@NamedQueries({
	@NamedQuery(name="AUTENTICA_USUARIO", query="from Usuario where email=:email and senha=:senha"),
	@NamedQuery(name="BUSCA_USUARIO_POR_EMAIL", query="from Usuario where email=:email"),
	@NamedQuery(name="BUSCA_USUARIO_ATIVO", query="from Usuario where email=:email and ativo='1'")
})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	private String email;
	private boolean ativo;
	private LocalDateTime dataInscricao;
	private String senha;
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public LocalDateTime getDataInscricao() {
		return dataInscricao;
	}
	public void setDataInscricao(LocalDateTime dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	
	
}
