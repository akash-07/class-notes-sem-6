import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket client = new DatagramSocket();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter message to send: ");
		String msg = sc.nextLine();
		DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName("10.21.24.202"), 1234);
		client.send(packet);
		System.out.println("Sent the packet");
		client.close();
		sc.close();
	}

}
