import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReadFileServer {

	public static void main(String[] args) throws IOException {
		String filename = "./src/UDPServer.java";
		
		String line = null;
		String msg = null;
		try {
			FileReader filereader = new FileReader(filename);
			
			BufferedReader bufferedReader = 
					new BufferedReader(filereader);
			
			while((line = bufferedReader.readLine()) != null)	{
				System.out.println(line);
				msg += line + "\n";		
			}
			
			bufferedReader.close();
		}
		catch(FileNotFoundException ex)	{
			System.out.println("Unable to open file: " + filename);
		}
		catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filename + "'");                  
        }
		DatagramSocket server = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName("10.21.24.212"), 1234);
		server.send(packet);
		System.out.println("File has been sent");
		server.close();
	}

}
