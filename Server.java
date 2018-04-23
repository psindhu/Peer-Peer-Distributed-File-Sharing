
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
 
// Server class
public class Server 
{
    public static void main(String[] args) throws IOException 
    {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);
         
        // running infinite loop for getting
        // client request
        while (true) 
        {
            Socket s = null;
             
            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();
                 
                System.out.println("A new client is connected : " + s);
                 
                // obtaining input and out streams
                ObjectInputStream dis = new ObjectInputStream(s.getInputStream());
                System.out.println("Stream" + s.getInputStream());
                System.out.println("Stream available" + s.getInputStream().available());
                System.out.println("Stream string" + s.getInputStream().toString());
                System.out.println("server name" + s.getLocalAddress().getHostName());
                
                 
                FileContent file = (FileContent)dis.readObject(); 
				InetAddress addr = InetAddress.getLocalHost();
                if(!fileSharedStatus(file,addr.getHostAddress())){
                	System.out.println("Assigning new thread for distributing and downloading the file");
            		System.out.println(file.toString());
            		
				    Thread t = new ClientHandler(s, file);
				    // Invoking the start() method
				    t.start();

                }
                else{
                	System.out.println("No thread since the file has already been distributed from this ip" + addr.getHostAddress() );
                }
                    	
               
                dis.close();
                 
            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
    
    public static boolean fileSharedStatus(FileContent file, String ip){
    	System.out.println("CHECKING THE SHARING STATUS" + ip);
		System.out.println("The file before checking" + file.toString());

    	for(String r:file.getRecievers()){
    		if(r.equals(ip)){
    			System.out.println("r" + r);
    			System.out.println("r-ip" + ip);
    			return true;
    		}
		}
    	for(String s:file.getSenders()){
    		if(s.equals(ip)){
    			System.out.println("s" + s);
    			System.out.println("s-ip" + s);
    			return true;
			}
		}
    	return false;
    	
    }
}