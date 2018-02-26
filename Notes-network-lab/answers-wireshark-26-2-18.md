**Name - Akash B Dhasade**

**Roll no - TCS15B031**

**1st.pcapng**

  - DHCP, MDNS, ARP, ICMP, ICMPv6


  - `ICMP` protocol is being used for sending ping requests and getting ping responses. So I will filter the packets to those that use `ICMP`. Then by looking at hosts that sent a ping request and did not receive a ping response, I can find out whether or not a host be able to ping to another host in the network. In the given network, all ping requests have received a ping response.


  - Response time = 0.393 ms


  - Two hosts are participating in the network. They are `10.0.0.2` and `10.0.0.1`.

  - Host `10.0.0.2` has sent a ping request to host `10.0.0.1`.

**2nd.pcapng**

  - Server Port - `8000`

  - Server - `10.0.0.1`, Client - `10.0.0.2`

  - The packet numbers are as follows:

  HTTP requests - 4, 18, 40.

  - The packet numbers are as follows:

  HTTP responses - 9, 32, 56.


  The response message to packet number 40 is -

  ```java
  import java.util.*;

  public class HelloWorld {
    public static void main(String[] args)  {
      System.out.println("Hello World");
    }
  }
  ```

**3rd.pcapng**

  - Fields in the UDP header are: source port, destination port, length, checksum.

  - The sizes in bytes of header fields are as follows:
    - source port: 2 bytes
    - destination port: 2 bytes
    - length: 2 bytes
    - checksum: 2 bytes

  data is of 6 bytes.

  - Value in the length field is the size of the UDP packet including headers and data. The length field indicates 14 bytes in this case accounting to 2 + 2 + 2 + 2 + 6(data).

  - Largest possible source port number is 2^16 - 1 = 65535

  - Protocol number for UDP: Decimal - 17, hexadecimal - 0x11

  -

| Field     | Hex Value     |
| :------------- | :------------- |
| IP: source IP address       | 0a00|
|....|0001      |
|Destination IP address| 0a00|
|....|0002|
|Protocol|0011|
|16 bit UDP padding length|000e|
|UDP: source port| 9751|
|UDP: dest port|04d2|
|UDP: Length|000e|
|UDP data: |4353|
|....|3332|
|....|3130|
|||
|Sum all hex values|15808|
|Carry|1|
|Add in the carry|5809|
|1's complement|A7F6|  

**4th.pcapng**

  - The most frequent port where all requests are heading can be termed as the port corresponding to the TCP server. The IP address can be identified as the server IP address.

  Server IP address: `10.0.0.3`
  Server port: `8888`

  Filter expression: `tcp.dstport = 8888`

  - 3 clients and 1 host is participating in the network.

  Clients - `10.0.0.1`, `10.0.0.2` and `10.0.0.4`

  Server - `10.0.0.3`

  - An IP address is associated with a NIC card. Hence we can use the filter `ip.src == [src ip address]` to monitor the outgoing packets from a specific system in a network.

  - The packets tagged as `[PSH, ACK]` carry the actual data. For ex. Packets numbered 115, 125, etc.

  Packets tagged as `[SYN]` and `[ACK]` are TCP control packets to do handshake and make the network perfect.

  We can also identify data packets by looking for those packets that have the `data` field in them.

  - We can use the following filter:

  `tcp.srcport == [port of 10.0.0.2] && tcp.dstort == [port of 10.0.0.3]`

  We can set the source and destination port numbers of the respective hosts to check the messages exchanged between them.

  We can also simply write:

  `tcp.port == [port number of host1] && tcp.port == [port number of host2]`

  - We can check the flowgraph of TCP packets by clicking on `statistics -> flowgraph` and then setting the flow type to `TCP flows`.

  - We can check the conversations by clicking on `statistics -> conversations`. Then we can choose appropriate protocol to get it's corresponding conversations.
