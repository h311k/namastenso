package pessoa;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class PessoaBean {

	private int id;
	private int idUsuario;
	private String fotoPerfil = "images/Generic-Profile.png";
	private Part arquivoFotoPerfil;
	private String primeiroNome;
	private String apelido;
	private String sobrenome;
	private Date dataNascimento;
	private String biografia;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	public Part getArquivoFotoPerfil() {
		return arquivoFotoPerfil;
	}
	public void setArquivoFotoPerfil(Part arquivoFotoPerfil) {
		this.arquivoFotoPerfil = arquivoFotoPerfil;
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
	
	public void importaFoto() {
		System.out.println(arquivoFotoPerfil.getSubmittedFileName());
	}
}
