package pessoa;

import java.io.IOException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class PessoaBean {

	@ManagedProperty(value="#{usuarioBean.idUsuario}")
	private int idUsuario;
	private String fotoPerfil = "images/Generic-Profile.png";
	private Part arquivoFotoPerfil;
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
		//Entra aqui metodo de validacao e renomeio de arquivo do perfil
		try {
			if(arquivoFotoPerfil.getContentType().equals("image/jpeg")) {
				
				//fotoPerfil = "C:\\Desenvolvimento\\arquivos\\perfil\\"+idUsuario+".jpg";
				fotoPerfil = "C:\\Desenvolvimento\\web\\upload\\perfil\\"+idUsuario+".jpg";
				arquivoFotoPerfil.write(fotoPerfil);
				fotoPerfil = "/foto-perfil/"+idUsuario+".jpg";
			} else {
				throw new RuntimeException("Arquivo com extensão não suportada.");
			}	
		} catch (IOException e) {
			throw new RuntimeException("O processamento do arquivo falhou");
			//e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void atualizaFoto() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.addCallbackParam("fotoPerfil", fotoPerfil);
	}
	
}
