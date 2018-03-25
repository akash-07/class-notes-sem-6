
import java.io.*;
import java.net.*;

class UDPServer
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[10];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);

                  ByteArrayInputStream bis = new ByteArrayInputStream(receiveData);
                  ObjectInput in = null;
                  in = new ObjectInputStream(bis);

                  //creating object from byte array using serialization
                  //I am using RDTACK only as the object being sent and received
                  RDTACK ack = (RDTACK) in.readObject();

                  //Printing the sequence number
                  int seq = ack.getSequenceNumber();
                  String sentence = new String( receivePacket.getData());
                  sentence = String.valueOf(seq);
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  String capitalizedSentence = "U sent " + seq;
                  sendData = capitalizedSentence.getBytes();
                  DatagramPacket sendPacket =
                     new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
               }
      }
}