
import java.io.*;
import java.net.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("localhost");
      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[10];
      for(int i = 0; i < 100; i++)
      {

         //creating object
         //I am using RDTACK only as the object
         RDTACK ack = new RDTACK(i);
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         ObjectOutput out = null;
         try {
             out = new ObjectOutputStream(bos);
             out.writeObject(ack);
             //using serialization to create byte array from object
             sendData = bos.toByteArray();
         }
         finally {
             bos.close();
         }

         DatagramPacket sendPacket =
                 new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
         clientSocket.send(sendPacket);
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         clientSocket.receive(receivePacket);
         String modifiedSentence = new String(receivePacket.getData());
         System.out.println("FROM SERVER: " + modifiedSentence);
      }
      clientSocket.close();
   }
}
