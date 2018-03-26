### Lab 26-3-18
**Akash Dhasade, TCS15B031**

1. Client/ Sender Ip address: `192.168.1.102` and port no: `38738`

2. Website "xyz" Ip address: `128.119.245.12` and port number: `80`

3. Packets numbered 55 and 58 contain initial handshaking messages. They have sequence number 0.

4. The sequence number of TCP SYN segment is 0 (frame no 55). The SYN flag is set in the segment headers for a TCP SYN segment. This identifies the segment as a SYN segment.

  ![image](https://user-images.githubusercontent.com/24961068/37906416-9f3eb834-311f-11e8-9996-ffc60951d94f.png)

5. a) The sequence number of SYNACK in reply to SYN is 0 and the packet or frame number is 58.

    b) The value of ACK field is 1. The "xyz" website determines this value as being the value/sequence number of next byte to be received.

    c) The SYN and ACK flags are set for a SYNACK segment.

  ![image](https://user-images.githubusercontent.com/24961068/37906468-dae8ec7e-311f-11e8-89ac-f29fad0eb5cb.png)

6. The sequence of ACK segment sent in response to SYNACK segment is 1. It's frame number is 59.

7. a) The sequence number of TCP segment containing HTTP POST command is 1. It's frame number is 60.

   b) TCP segment data is of 1358 bytes in the HTTP Post request.

8.  `--` means that the ACK packet was dropped but still TCP functions well beacause of it's cumulative acks.
  
    a), b), c), d) Given in table
   
     e) Packets 2 and 3 received a combined ACK. The entry in for packet no. 2 should ideally be same as packet no. 3 because the ACK received for packet 3 acts as an ACK for packet 2 whose ACK was dropped.
  
  | S.No     | Frame No.|Length of TCP segment| Seq No.| Sent time| Ack frame No. | Ack No. | Ack received time| RTT (seconds)|
  | :------------- | :------------- |:------------- |:------------- | :------------- |:------------- | :------------- |:------------- | :------------- |
  | 1       | 60 | 1358 | 1 | 12.726670472| 70| 1359| 13.366188730| 0.639518258|
  | 2      | 61 | 1358 | 1359 | 12.726695310| --|-- |-- | --|
  | 3      | 62 | 1358 |2717 | 12.726709598| 73|4075 |13.366190257 | 0.639480659|
  | 4      | 63 | 1358 |4075 | 12.726721285| 78|5433 |13.366191089 | 0.639469804|
  | 10      | 69 | 1358 |12223 | 12.726817540| 85|13581 |13.366570639 | 0.639753099|
  | 11      | 71 | 1358 |13581 | 13.366306179| 96|14939 |14.005899504 | 0.639593325|
  | 25      | 90 | 1358 |32593 | 13.366782686| 132|32769 |14.006303689 | 0.639521003|

    f)
  
  ![image](https://user-images.githubusercontent.com/24961068/37906474-dffd543e-311f-11e8-9496-8440ab761587.png)

9) We can check the sequence numbers of packets being transmitted to see if any packet is dropped and sent again. Since the sequence numbers are monotonically increasing, we can say that no packet has been dropped. The same can be observed from the following graph:
  
  ![image](https://user-images.githubusercontent.com/24961068/37906480-e3f4662c-311f-11e8-8adc-18b459869cc9.png)

10) We consider the last packet as the packet which was received in response to FIN ACK sent the sender to website "xyz". This corresponds to packet with frame number 268.

Time2 = 20.444247480 (frame no 268)

Time1 = 12.087679615 (frame no 55)

Data sent = 149322 bytes

We take time1 as the time when SYN packet was first sent. This corresponds to frame number 55. Amount of data sent can be calculated as the 1 minus the ACK number of last ACK that we considered.

Throughput = Data sent / Time2 - Time1
Throughput =  149322/ 8.356567865 = 17868.8191626 bytes/sec
