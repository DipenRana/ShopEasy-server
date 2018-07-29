

import java.io.IOException;
import java.net.ServerSocket;


public class ConnectionManager {
   
    public static ConnectionSE connect(){
        ServerSocket serverSocket=null;
        try {
            serverSocket=new ServerSocket(9180);
            System.out.println("server created");
            return new ConnectionSE(serverSocket.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
  
}
