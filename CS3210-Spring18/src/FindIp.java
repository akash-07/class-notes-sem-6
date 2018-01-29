import java.net.InetAddress;
import java.net.UnknownHostException;

public class FindIp {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		System.out.println(InetAddress.getByName("www.iittp.ac.in").getHostAddress());
		
	}

}
