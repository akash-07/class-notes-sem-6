import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class RDTSender {

    public static void main(String[] args)  throws Exception{
        //Creating client socket
        DatagramSocket clientSocket = new DatagramSocket();
        final int PACKET_SIZE = 32;
        byte[] sendData = new byte[32];
        byte[] recData = new byte[1024];

        //Put any message here
        String message = "Write simple java program to convert an object into byte array and byte\n" +
                "array to object using serialization concept. Send the object (which is\n" +
                "converted to bytes) through Socket and retrieve the byte stream of the object\n" +
                "at receiver side and print the contents of the object.";

        byte[] entire_data = message.getBytes();
        int num_packets = entire_data.length / PACKET_SIZE + 1;
        InetAddress IPAddress = InetAddress.getByName("localhost");
        System.out.println("Number of packets = " + num_packets);
        boolean last = false;

        for(int j = 0; j < num_packets; j++)    {
            if(j == num_packets - 1)
                last = true;

            //creating byte array for sending
            sendData = new byte[32];
            for(int i = 0; i < PACKET_SIZE ; i++)    {
                if(PACKET_SIZE * j + i >= entire_data.length)   {
                    break;
                }
                sendData[i] = entire_data[PACKET_SIZE * j + i];
            }

            RDTPacket sendPacket = new RDTPacket(j, sendData, last);

            //Converting the packet to byte stream by serialization
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(sendPacket);
            byte[] rdt_packet_as_byte_array = bos.toByteArray();
            DatagramPacket dataPacket = new DatagramPacket(rdt_packet_as_byte_array,
                    rdt_packet_as_byte_array.length, IPAddress, 4444);
            clientSocket.send(dataPacket);

            //Waiting to receive ack packet
            DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
            clientSocket.receive(recPacket);

            //Converting ack packet into ack object
            ByteArrayInputStream bis = new ByteArrayInputStream(recData);
            ObjectInput in = new ObjectInputStream(bis);
            RDTACK rdtack = (RDTACK) in.readObject();

            System.out.println("Received ACK [" + rdtack.getSequenceNumber() + "]");

            //Just to visualize simulation
            TimeUnit.SECONDS.sleep(10);

        }
    }
}
