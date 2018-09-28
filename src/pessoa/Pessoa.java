package pessoa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author haony
 * 
 * Entidade de Pessoa refletindo a tabela de pessoas do BD.
 *
 */
@Entity
@Table(name="pessoas")
public class Pessoa {

	@Id
	private int idUsuario;
	private String primeiroNome;
	private String apelido;
	private String sobrenome;
	private Date dataNascimento;
	private String biografia;
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	public Pessoa() {
		
	}
	public Pessoa(int idUsuario, String primeiroNome, String apelido, String sobrenome, Date dataNascimento, String biografia) {
		this.idUsuario = idUsuario;
		this.primeiroNome = primeiroNome;
		this.apelido = apelido;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.biografia = biografia;
	}
}
