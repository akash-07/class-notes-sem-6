import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by @kash on 3/25/2018.
 */

/*GoBACK 3 protocol implemented as given in book.
* There is a single timer for the whole window.
* Whenever an expected ACK is received the timer is reset.
* Hence, the timer at any point of time denotes time for the first packet
* of the window which is unacked.*/
public class GOBackNSender {
    public int drop = 0;
    public final int MSS = 20;
    public DatagramSocket senderSocket;
    public InetAddress receiverAddress;
    public int receiverPortNumber;
    public Timer timer;
    int windowSize = 3;
    int transmitStartIndex = 0;
    boolean last = false;
    byte[] appBytes;
    int seq = 0;
    int lastUnacked = 0;
    Random random = new Random();

    GOBackNSender(DatagramSocket senderSocket, InetAddress receiverAddress, int receiverPortNumber, int windowSize, byte[] appBytes) {
        this.senderSocket = senderSocket;
        this.receiverPortNumber = receiverPortNumber;
        this.receiverAddress = receiverAddress;
        this.windowSize = windowSize;
        this.appBytes = appBytes;
    }
    public static void main(String[] args) throws Exception  {
        DatagramSocket senderSocket = new DatagramSocket();
        InetAddress receiverAddress = InetAddress.getByName("localhost");
        int receiverPortNumber = 9786;
        final int windowSize = 3;
        String sentence = "Sender should send data packets with sequence number, and a timer\n" +
                "needs to be set for each packet sent. Receiver receives the first packet\n" +
                "and sends acknowledgement. The process repeats in a similar way. ";

        byte[] appBytes = sentence.getBytes();

        GOBackNSender goBackNSender = new GOBackNSender(senderSocket, receiverAddress,
                receiverPortNumber, windowSize, appBytes);

        System.out.println("Application data: [" + appBytes.length + " bytes]");
        /* Curated logic:
        * Initially send N packets
        * wait to receive ack
        * if ack == expected ack
        *   send the next packet, shift the window
        * else
        *   ignore the ack
        * on timer timeout -> send the whole window again  */
        goBackNSender.transmit();
    }

    //This function also does slicing into packets
    //transmitStartIndex is only updated by receiever functions
    void sendN() throws IOException {
        for(int i = 0; i < windowSize; i++) {
            byte[] appChunkBytes;
            if(transmitStartIndex + MSS*(i+1) > appBytes.length) {
                last = true;
                appChunkBytes = Arrays.copyOfRange(appBytes, transmitStartIndex + MSS*i, appBytes.length);
                System.out.println("Last is true");
            }
            else {
                appChunkBytes = Arrays.copyOfRange(appBytes, transmitStartIndex + MSS*i, transmitStartIndex + MSS*(i+1));
            }
            sendPacket(appChunkBytes, transmitStartIndex/ MSS + i);
            if(last)
                break;
        }
    }

    //Just sends a single packet given data and sequence number
    //last is a global variable
    void sendPacket(byte[] data, int seq) throws IOException  {
        System.out.println("Sending packet [" + seq + "]");

        RDTPacket rdtPacket = new RDTPacket(seq, data, last);


        //convert the packet into byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(rdtPacket);
        byte[] rdt_pkt_as_byte_array = bos.toByteArray();

        //creating a datagram packet
        DatagramPacket datagramPacket = new DatagramPacket(rdt_pkt_as_byte_array,
                rdt_pkt_as_byte_array.length, receiverAddress, receiverPortNumber);
        senderSocket.send(datagramPacket);
    }

    void startTimer()   {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timeout for current window");
                try{
                    sendN();
                }
                catch(Exception e)  {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
            }
        };
        timer.schedule(task, 3000, 3000);
    }

    int receiveAck()  throws Exception  {
        byte[] recData = new byte[1024];
        DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
        senderSocket.receive(recPacket);

        //convert the received packet byte strem into ACK object
        ByteArrayInputStream bin = new ByteArrayInputStream(recData);
        ObjectInput in = new ObjectInputStream(bin);
        RDTACK rdtack = (RDTACK) in.readObject();
        System.out.println("Received ACK [" + rdtack.getSequenceNumber() + "]");
        return rdtack.getSequenceNumber();
    }

    // last unacked = transmitStartIndex / MSS
    // transmit start index denotes the first packet of the window
    void transmit() throws Exception{
        System.out.println("Total #packets to send: [" + (appBytes.length/MSS + 1) + "]" );
        int recSeqNum;
        sendN();
        startTimer();
        while(true)    {
             System.out.println("Waiting to receive ACK..");
             recSeqNum = receiveAck();
             //if sequence number is as expected
             if(transmitStartIndex/ MSS == recSeqNum)   {
                 stopTimer();
                 transmitStartIndex += MSS;
                 if(transmitStartIndex > appBytes.length)
                     break;
                 //System.out.println(transmitStartIndex + " " + appBytes.length);
                 int windowStart  = transmitStartIndex/ MSS;
                 System.out.println("Window: [" + windowStart + "][" + ++windowStart + "][" + ++windowStart + "]");
                 if(!(transmitStartIndex + MSS * windowSize > appBytes.length))  {
                     byte[] appChunkBytes;
                     if(transmitStartIndex + MSS * windowSize > appBytes.length) {
                         last = true;
                         appChunkBytes = Arrays.copyOfRange(appBytes, transmitStartIndex + MSS * (windowSize - 1), appBytes.length);
                     }
                     else {
                         appChunkBytes = Arrays.copyOfRange(appBytes, transmitStartIndex + MSS * (windowSize - 1), transmitStartIndex + MSS * windowSize);
                     }
                     if(random.nextFloat() < 0.7f)
                        sendPacket(appChunkBytes, transmitStartIndex/ MSS + (windowSize - 1));
                     else
                         System.out.println("Packet [" + (transmitStartIndex/ MSS + (windowSize - 1)) + "] dropped");
                     last = false;
                 }
                 startTimer();
             }
             //discard the ack
             else   {
                 System.out.println("Discarding received ack [" + recSeqNum + "]");
             }
            System.out.println();
        }
    }

    void stopTimer()    {
        timer.cancel();
        timer.purge();
    }
}
