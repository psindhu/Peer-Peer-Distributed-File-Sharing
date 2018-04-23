import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ClientHandler extends Thread 
{
    final Socket s;
    FileContent file;
 
    // Constructor
    public ClientHandler(Socket s, FileContent file) 
    {
        this.s = s;
        this.file = file;
    }
 
    @Override
    public void run() 
    {

        try {
			 synchronized (this) {

        		FileOutputStream fout = new FileOutputStream("image.jpg");
                fout.write(file.getContent());
				System.out.println(file.toString());
				// closing resources
	            fout.close();
        		
	            sendFile(file);
		            	
        		this.s.close();
			 }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
}

	private void sendFile(FileContent file) {
		try
        {
        	Scanner scn = new Scanner(System.in);
            scn =new Scanner (new File("neighbors.txt"));
            List<String> recievers;
			System.out.println(file.toString());
			InetAddress addr = InetAddress.getLocalHost();
			boolean setreceiver = false;
			while (scn.hasNext()) {
				String ipString = scn.nextLine();
				System.out.println("CHECKING THE NEIGHBOUR" + ipString);
				InetAddress ip = InetAddress.getByName(ipString);
				
				//Setting the receiver ip
				if(!setreceiver){
					if(file.getRecievers() == null || file.getRecievers().isEmpty()){
             			recievers =  new ArrayList<String>();
             			recievers.add(addr.getHostAddress());
             		}
             		else{
             			recievers = file.getRecievers();
             			recievers.add(addr.getHostAddress());
             		}
             		file.setRecievers(recievers);
             		setreceiver = true;
             	}
             	
				// establish the connection with server port 5056
				Socket s = new Socket(ip, 5056);
				ObjectOutputStream dosout = new ObjectOutputStream(s.getOutputStream());
				dosout.writeObject(file);
			            
				dosout.flush();
				dosout.close();
				s.close();
			}
        	

		      
        }catch(Exception e){
            e.printStackTrace();
        }

		
	}

	
}