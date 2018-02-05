import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
	public static void main(String[] args) throws IOException	{
		DatagramSocket server = new DatagramSocket(1234);
		byte[] buffer = new byte[256];
		DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
		server.receive(inPacket);
		String msg = new String(buffer);
		System.out.println("The received msg is: " + msg);
		server.close();
	}
}
