import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Arrays;


public class RDT3Receiver {
	
	
	public static void main(String[] args) throws Exception{
		
		DatagramSocket receiveSocket = new DatagramSocket(9876);
		
		// 83 is the base size (in bytes) of a serialized RDTPacket object 
		byte[] receiveData = new byte[RDT3Sender.MSS+83];
		int expectedSeqNum = 0;
        ArrayList<String> buffer = new ArrayList<>();

		while(true){
			
			System.out.println("RDT receiver waiting for the packet..");
			
			// Receive packet
			/* implement your logic here */
            DatagramPacket recPacket = new DatagramPacket(receiveData, receiveData.length);
            receiveSocket.receive(recPacket);

            //convert the received packet into an rdt packet
            ByteArrayInputStream bin = new ByteArrayInputStream(receiveData);
            ObjectInput in = new ObjectInputStream(bin);
            RDTPacket packet = (RDTPacket)in.readObject();
            if(packet.getSeq() == expectedSeqNum){
                if(expectedSeqNum == 0)
                    expectedSeqNum = 1;
                else
                    expectedSeqNum = 0;
                System.out.println("Packet [" + packet.getSeq() + "] received. Sending acknowledgement.");
                System.out.println("Data: " + new String(packet.data));
                buffer.add(new String(packet.data));
            }

            // Create an RDTAck object
            RDTACK ack = new RDTACK (packet.getSeq());

            // Serialize
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(ack);

            byte[] ackBytes = bos.toByteArray();

            DatagramPacket ackPacket = new DatagramPacket(ackBytes, ackBytes.length, recPacket.getAddress(), recPacket.getPort());
            receiveSocket.send(ackPacket);


            System.out.println("Sending ACK [" + packet.getSeq() + "] with " + ackBytes.length  + " bytes");
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

		
	