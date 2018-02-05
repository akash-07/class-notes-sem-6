import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class WriteFileClient {

	public static void main(String[] args) throws IOException {
		DatagramSocket client = new DatagramSocket(1234);
		byte[] buffer = new byte[1024];
		DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
		client.receive(inPacket);
		String msg = new String(buffer);
		System.out.println("The received msg is: " + msg);
		client.close();
	}

}
