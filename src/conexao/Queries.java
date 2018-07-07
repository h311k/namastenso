package conexao;

public class Queries {

	public static String AUTENTICA_USUARIO = "select email, senha, ativo, dataInscricao from usuarios where email=? and senha=?";
	
}
