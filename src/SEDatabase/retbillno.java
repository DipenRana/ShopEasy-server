package SEDatabase;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class retbillno {
	public static int main(){
		  java.sql.Connection dbcon = null;
		  int billno = 560000;
			try {

				Class.forName("com.mysql.jdbc.Driver");
				dbcon = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/shopeasy",
								"root", "tiger");
			
				String sql = "Select max(bill_no)as idrf from Bill;";
				PreparedStatement ps = dbcon.prepareStatement(sql);	
				 ResultSet rs = ps.executeQuery();
				 while (rs.next()) 
			        {
			            billno =rs.getInt("idrf");
			            System.out.println(billno);
			        } 
				 if(billno==0)
					 billno=560000;
				 return billno;
			}catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
			return billno;
	}
}
