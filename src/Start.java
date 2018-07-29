
import java.io.IOException;

public class Start {

	static ConnectionSE con;
	//static Thread ServerThread;

	public static void main(String[] args) {
		
		//new UInterface();
		//ServerThread=new Thread(new Runnable() {
			
		//	public void run() {
				con = ConnectionManager.connect();
				System.out.println("Connected");
				BarcodeReciever cr = new BarcodeReciever(con);
				try {
					cr.start();
				} catch (IOException e) {
					//e.printStackTrace();
					System.out.println("Disconnected");
				}
		//	}
		//});

	}

}