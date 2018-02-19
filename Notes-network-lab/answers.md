**3rdsimplewebpagerequestandresponsefollowtcpstream.pcapng**

- Sender is: 180.149.59.136
(assumption: sender is server)
- Receiver is: 10.21.24.231
(assumption: receiver is client who receives HTTP response)
- Many requests are made and many responses are received.
  1. success.txt
  2. lastpage.html
  3. favicon.ico
  4. success.txt (2nd time)

  POST requests were also made by the client. The responses were received via OCSP protocol.

  *Note - I haven't showed the response message here as the html text received could not be copied.*

**6thSimpleSMTP.pcapng**

- email sender: 10.0.0.3, 10.0.0.2
- server: 10.0.0.1
- mail content:

10.0.0.2:

mail from: abc@123.com

mail to: dfg@123.com

data: here is one more message

10.0.0.3:

from: 123@abc.com

to: 456@abc.com

data: Hi CS2310

**9thMultichatclientservercapture.pcapng**

- hosts with IP addresses: 10.0.0.[1-4]
- we can say `ip.src == 10.0.0.1`, this filters out packets where `10.0.0.1` is source.
- Handshaking packets can be guessed by checking packets that look different than usual packets. Here are some of them:

`22	21.386902351	10.0.0.1	10.0.0.3	TCP	74	2222 → 35936 [SYN, ACK] Seq=0 Ack=1 Win=28960 Len=0 MSS=1460 SACK_PERM=1 TSval=3940330718 TSecr=2189746675 WS=512`

`37	42.218025739	10.0.0.1	10.0.0.4	TCP	74	2222 → 57348 [SYN, ACK] Seq=0 Ack=1 Win=28960 Len=0 MSS=1460 SACK_PERM=1 TSval=3049298629 TSecr=3690765773 WS=512`

- host ip `10.0.0.1` asks every newly joined host for their name. And once the host enters it's name, `10.0.0.1` says `Welcome <name> to out chat room..To leave enter /quit in a new line.`
