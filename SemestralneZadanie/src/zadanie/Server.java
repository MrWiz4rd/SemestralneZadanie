package zadanie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Server {
	public static Connection connect() throws SQLException {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:Knihy.db");
			//System.out.println("PRIPOJENY!!!!!!!!!!");
	} catch (ClassNotFoundException e) {
		System.out.println(e+"");
	}
		
		return con;
}
}

