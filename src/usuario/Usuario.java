package usuario;


/**
 * @author haony
 * 
 * Entidade de Usuário refletindo a tabela de usuarios do BD.
 *
 */
public class Usuario {

	private int id;
	private String email;
	private boolean ativo;
	private String dataInscricao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
