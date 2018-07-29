

import java.io.ObjectOutputStream;
import SEDatabase.DBDetails;
import java.io.Serializable;


public class BarcodeSender implements Serializable{
	
	 private ConnectionSE con;
	 
	 public BarcodeSender(ConnectionSE con){
		 this.con=con;
	 }

	 public void send(DBDetails db) {
	        try {
	            ObjectOutputStream oos = new ObjectOutputStream(con.getOutputStream());
	            oos.writeObject(db);
	            oos.flush();
	          
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
}
