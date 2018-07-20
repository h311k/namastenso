package usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@NamedQuery(name="VERIFICA_EXISTENCIA_USUARIO", query="from Usuario where email=:email"),
	@NamedQuery(name="VERIFICA_USUARIO_ATIVO", query="from Usuario where email=:email and ativo='0'")
})
public class Usuario {
	
	@Id
	@GeneratedValue
	private int idUsuario;
	private String email;
	private boolean ativo;
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
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getDataInscricao() {
		return dataInscricao;
	}
	public void setDataInscricao(String dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	
	
}
