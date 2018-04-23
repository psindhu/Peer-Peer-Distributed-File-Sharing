import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

 
// Client class
public class Client 
{
    public static void main(String[] args) throws IOException 
    {
        try
        {
        	int dia = 1;
            Scanner scn = new Scanner(System.in);
            scn =new Scanner (new File("neighbors.txt"));
            List<String> senders;

            for (int i=0;i<dia+1;i++){
            	while (scn.hasNext()) {
            		String ipString = scn.nextLine();
            		InetAddress ip = InetAddress.getByName(ipString);
     				InetAddress addr = InetAddress.getLocalHost();

            		// establish the connection with server port 5056
            		Socket s = new Socket(ip, 5056);
     				System.out.println("local address" + addr.getHostAddress());

            		FileContent file = new FileContent();
            		File image = new File("image.jpg");
            		byte fileContent[] = new byte[(int)image.length()];
            		file.setContent(fileContent);

            		if(file.getSenders() ==null || file.getSenders().isEmpty()){
            			senders =  new ArrayList<String>();
            			senders.add(addr.getHostAddress());
            		}
            		else{
            			senders = file.getSenders();
            			senders.add(addr.getHostAddress());
            		}

            		file.setSenders(senders);

            		ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
            		dos.writeObject(file);
            		System.out.println(file.toString());
            		// closing resources
            		dos.flush();
            		dos.close();             
            		s.close();
        		}
        	}
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}