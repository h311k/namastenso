package pessoa;

import javax.persistence.EntityManager;

import conexao.FabricaConexao;

public class PessoaDAO {

	/**
	 * Cria pessoa associada ao usuario (perfil)
	 * 
	 */
	protected void salvaPessoa(Pessoa pessoa) {
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		manager.getTransaction().begin();
		manager.merge(pessoa);
		manager.getTransaction().commit();
		manager.close();
	}
}
