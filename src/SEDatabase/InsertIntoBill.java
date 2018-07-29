package SEDatabase;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class InsertIntoBill {

	public InsertIntoBill(String curdate, int payId, float total){
		java.sql.Connection dbcon = null;
	

		try {

			Class.forName("com.mysql.jdbc.Driver");
			dbcon = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/shopeasy", "root", "tiger");

		
			String sql = "INSERT INTO Bill (date,payment_id,bill_amt)"
					+ "VALUES (?,?,?);";
			
			PreparedStatement ps = dbcon.prepareStatement(sql);
			
			ps.setString(1,curdate);
			ps.setInt(2, payId);
			ps.setFloat(3,total);
			 ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
