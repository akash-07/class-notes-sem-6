import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.*;

public class RDT3Sender {
    // Maximum Payload Segment Size - Quantity of data from the application layer in the segment
	public static int drop=0;
	public static final int MSS = 7;

	public static DatagramSocket senderSocket;
    public static InetAddress receiverAddress;
    public static int receiverPortNumber;

    public static Timer timer;

    public static void main(String[] args) throws Exception{

    senderSocket = new DatagramSocket();
	// Receiver address
	receiverAddress = InetAddress.getByName("localhost");
	receiverPortNumber = 9876;

	Scanner in = new Scanner(System.in);
	//System.out.println("Enter a statement to transfer: ");
	//String sentence = in.nextLine();
	String sentence = "Sender should send data packets with sequence number, and a timer\n" +
            "needs to be set for each packet sent. Receiver receives the first packet\n" +
            "and sends acknowledgement. The process repeats in a similar way. ";
	byte[] appBytes = sentence.getBytes();

	System.out.println("App Data size: " + appBytes.length + " Bytes");

	int transmitStartIndex = 0;
	int seqNum = 0; 
    boolean last = false;
    Timer timer;

	while(transmitStartIndex < appBytes.length){
	    byte[] appChunkBytes = new byte[MSS];
	    // Copy segment of data bytes to array
	    appChunkBytes = Arrays.copyOfRange(appBytes, transmitStartIndex, transmitStartIndex + MSS);
        Random random =  new Random();
        if(transmitStartIndex + MSS > appBytes.length)  {
            last = true;
            System.out.println("Last is true");
        }
        //drop packet with 70% probability
        if(random.nextFloat() < 0.7f)
	        sendPacket(appChunkBytes, seqNum, last);
	    startTimer(appChunkBytes, seqNum, last);
	    if(seqNum == receiveAck()){
		    /* implement your logic here */
            transmitStartIndex += MSS;
            stopTimer();
	        if(seqNum == 0)
	            seqNum = 1;
	        else
	            seqNum = 0;
	    }
	
	    System.out.println();
	}
	in.close();
    }

    private static void stopTimer() {
        timer.cancel();
        timer.purge();
    }
    
    public static void startTimer(byte[] appData, int seq, boolean last) {
    	/*implement your logic here */
    	//Every time I instantiate
    	timer = new Timer();
    	TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try{
                    System.out.println("Timeout for packet [" + seq + "]");
                    sendPacket(appData, seq, last);
                }
                catch(Exception e)  {
                    System.out.println(e.getMessage());
                }
            }
        };
    	//Start after initial delay of 3s
    	timer.schedule(task, 3000, 3000);
    }
   
    public static void sendPacket(byte[] appData, int seq, boolean last) throws IOException
    {
    	System.out.println("Sending packet [" + seq + "]");

        /*implement your logic here */
        RDTPacket packet = new RDTPacket(seq, appData, last);

        //Convert the packet object to byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(packet);
        byte[] rdt_packet_as_byte_array = bos.toByteArray();

        //Creating a datagram packet
        DatagramPacket datagramPacket = new DatagramPacket(rdt_packet_as_byte_array,
                rdt_packet_as_byte_array.length, receiverAddress, receiverPortNumber);
        senderSocket.send(datagramPacket);
    }

    public static int receiveAck() throws IOException, ClassNotFoundException{
    	
    	/*implement your logic here */
    	byte[] recData = new byte[1024];
    	DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
    	senderSocket.receive(recPacket);

    	//convert the received packet byte stream into ACK object
        ByteArrayInputStream bin = new ByteArrayInputStream(recData);
        ObjectInput in = new ObjectInputStream(bin);
        RDTACK rdtack = (RDTACK) in.readObject();
        System.out.println("Received ACK [" + rdtack.getSequenceNumber() + "]");
        return rdtack.getSequenceNumber();
    }
}