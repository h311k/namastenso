package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

	public Connection getConexao() {
		
		Connection conexao = null;
		try {
			Class.forName(DadosConexao.DRIVER);
			conexao = (Connection) DriverManager.getConnection(DadosConexao.URL);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return conexao;
	}
}
