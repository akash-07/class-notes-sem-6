import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

/**
 * Created by @kash on 3/25/2018.
 */
public class GOBackNReceiver {
    static int MSS = 20;

    public static void main(String[] args) throws Exception {
        DatagramSocket receiveSocket = new DatagramSocket(9786);

        byte[] receiveData = new byte[MSS + 512];
        int expectedSeqNum = 0;
        ArrayList<String> buffer = new ArrayList<>();

        while(true) {
            System.out.println("RDT receiver waiting for packet...");

            DatagramPacket recPacket = new DatagramPacket(receiveData, receiveData.length);
            receiveSocket.receive(recPacket);

            //convert the received ack into an rdt packet
            ByteArrayInputStream bin = new ByteArrayInputStream(receiveData);
            ObjectInput in = new ObjectInputStream(bin);
            RDTPacket packet = (RDTPacket)in.readObject();
            if(packet.getSeq() == expectedSeqNum)   {
                expectedSeqNum++;
                buffer.add(new String(packet.data));
            }
            //cumulative acknowledgement
            RDTACK ack = new RDTACK(expectedSeqNum - 1);
            System.out.println("Packet [" + packet.getSeq() + "] received. Sending acknowledgement.");
            System.out.println("Data: " + new String(packet.data));

            // Serialize
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(ack);

            byte[] ackBytes = bos.toByteArray();

            DatagramPacket ackPacket = new DatagramPacket(ackBytes, ackBytes.length, recPacket.getAddress(), recPacket.getPort());
            receiveSocket.send(ackPacket);

            System.out.println("Sending ACK [" + ack.getSequenceNumber() + "] with " + ackBytes.length  + " bytes");
            if(packet.last) {
                System.out.println("Received the last packet. Sending data to Application layer.\n");
                for(String s: buffer)   {
                    System.out.print(s);
                }
                break;
            }
            System.out.println();
        }
    }
}
