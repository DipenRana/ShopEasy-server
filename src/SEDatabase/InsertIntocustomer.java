package SEDatabase;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertIntocustomer {

	public InsertIntocustomer( String name, String addr, String contact, Integer billno){
		
		java.sql.Connection dbcon = null;
		

		try {

			Class.forName("com.mysql.jdbc.Driver");
			dbcon = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/shopeasy", "root", "tiger");

		
			String sql = "INSERT INTO Customer (name,address,contact,bill_no)"
					+ "VALUES (?,?,?,?);";
			
			PreparedStatement ps = dbcon.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setString(2, addr);
			ps.setString(3,contact);
			ps.setInt(4,billno);
			 ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
