package SEDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public static String main(String bcode) throws SQLException,
			ClassNotFoundException {

		Connection dbcon = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			dbcon = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/shopeasy", "root", "tiger");

			stmt = dbcon.createStatement();
			ResultSet rs = stmt
					.executeQuery("Select * from Items where barno= " + bcode
							+ ";");

			if (rs.next()) {
				return rs.getString("name") + "@" + rs.getString("price");
			}

			dbcon.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Not Found";
		}

		return "Not Found";
	}
}
