package pessoa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.context.RequestContext;

import usuario.UsuarioBean;

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
	private String dataNascimento;
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
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	@PostConstruct
	public void init() {
		PessoaDAO pd = new PessoaDAO();
		Pessoa pessoa = pd.carregaPessoa(idUsuario);
		try {
			FileReader fr = new FileReader(new File("C:\\Desenvolvimento\\web\\upload\\perfil\\"+idUsuario+".jpg"));
			fotoPerfil = "/foto-perfil/"+idUsuario+".jpg";
		} catch (FileNotFoundException e) {
			fotoPerfil = "images/Generic-Profile.png";
		}
		primeiroNome = pessoa.getPrimeiroNome();
		apelido = pessoa.getApelido();
		sobrenome = pessoa.getSobrenome();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = df.format(pessoa.getDataNascimento());
		biografia = pessoa.getBiografia();
		
	}
	
	public void importaFoto() {
		//Entra aqui metodo de validacao e renomeio de arquivo do perfil
		try {
			if(arquivoFotoPerfil.getContentType().equals("image/jpeg")) {
				fotoPerfil = "C:\\Desenvolvimento\\web\\upload\\perfil\\"+idUsuario+".jpg";
				arquivoFotoPerfil.write(fotoPerfil);
				fotoPerfil = "/foto-perfil/"+idUsuario+".jpg";
			} else {
				throw new RuntimeException("Arquivo com extensão não suportada.");
			}	
		} catch (IOException e) {
			throw new RuntimeException("O processamento do arquivo falhou");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void atualizaFoto() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.addCallbackParam("fotoPerfil", fotoPerfil);
	}
	
	@SuppressWarnings("deprecation")
	public void atualizaPerfil() {
		Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String primeiroNome = requestParamMap.get("primeiroNome");
		String apelido = requestParamMap.get("apelido");
		String sobrenome = requestParamMap.get("sobrenome");
		String dataNascimento = requestParamMap.get("dataNascimento");
		String biografia = requestParamMap.get("biografia");
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = df.parse(dataNascimento);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		PessoaDAO pd =  new PessoaDAO();
		pd.salvaPessoa(idUsuario, primeiroNome, apelido, sobrenome, data, biografia);
		requestContext.addCallbackParam("retornoAtualizaPerfil", "ok");
	}
	
}
