package pessoa;

import java.util.Date;

import javax.persistence.EntityManager;

import conexao.FabricaConexao;

public class PessoaDAO {

	/**
	 * Cria pessoa associada ao usuario (perfil)
	 * 
	 */
	protected void salvaPessoa(int idUsuario, String primeiroNome, String apelido, String sobrenome, Date dataNascimento, String biografia) {
		Pessoa pessoa = new Pessoa(idUsuario, primeiroNome, apelido, sobrenome, dataNascimento, biografia);
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		manager.getTransaction().begin();
		manager.merge(pessoa);
		manager.getTransaction().commit();
		manager.close();
	}
	
	/**
	 * Busca pessoa por idPessoa (perfil)
	 * 
	 * @param idUsuario
	 * @return Pessoa
	 */
	protected Pessoa carregaPessoa(int idUsuario) {
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		Pessoa pessoa = manager.find(Pessoa.class, idUsuario);
		return pessoa;	
	}
}
