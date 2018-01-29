import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPMyServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * First time the packet comes, it is addressed by ServerSocket (server). Later 
		 * it is taken up by Socket (clientSocket). 
		 * 
		 *  Hotel analogy:
		 *  ServerSocket - reception
		 *  Socket - room
		 *  That is you go to reception only once but you visit/leave rooms after that 
		 *  frequently. */
		ServerSocket server = new ServerSocket(1111);
		Socket childSocket = server.accept();
		PrintWriter out = new PrintWriter(childSocket.getOutputStream());
		// HTTP protocol
		out.println("HTTP/1.0 200 OK\r\n");
		out.println("\r\n");
		out.println("<TITLE> Welcome to my website </TITLE>");
		out.println("<H1>This is newtork's class</H1>");
		out.close();
		childSocket.close();
		server.close();
	}

}
