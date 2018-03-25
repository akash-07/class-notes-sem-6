import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class RDTReceiver {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(4444);
        byte[] recData = new byte[1024];
        byte[] send_packet_as_byte_array = new byte[1024];

        while(true) {
            DatagramPacket inPacket = new DatagramPacket(recData, recData.length);
            serverSocket.receive(inPacket);

            ByteArrayInputStream bis = new ByteArrayInputStream(recData);
            ObjectInput in = new ObjectInputStream(bis);
            RDTPacket rdtPacket = (RDTPacket) in.readObject();
            System.out.println("[" + rdtPacket.getSeq() + "]" + new String(rdtPacket.getData()));

            //creating the ack packet
            RDTACK rdtack = new RDTACK(rdtPacket.getSeq());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(rdtack);
            send_packet_as_byte_array = bos.toByteArray();
            DatagramPacket ackPacket = new DatagramPacket(send_packet_as_byte_array,
                    send_packet_as_byte_array.length, inPacket.getAddress(), inPacket.getPort());
            serverSocket.send(ackPacket);

            //if last packet then terminate
            if(rdtPacket.last)
                break;
        }
    }
}
