# Notes Class-1

- ssh -X user@10.21.27.114 -> Connect to remote VM

- new address: 10.21.24.101

- logout -> to logout

- password -> test@123

- ifconfig -> details about network interfaces and ip-addresses

- ping (ip-address) -c 2 -> 2 packets will be sent to the specified ip address

- ping localhost -> send and receive data to own machine

- sudo apt-get install emacs -> install emacs

- traceroute (ip-address) ->
1. Finds the number of routers between you and the specified ip address
2. Time shown is the roundtrip time taken for sending and receiving packets. It does three trips for the same, hence three time outputs.

**No router between you and neighbour, still you get 1 as the output, why?**

- ping (ip-address) -w time  -> specify the amount of time you want to keep pinging

- ifconfig eth down -> cut down connection

- sudo mn -> starts mininet and creates two hosts with virtual hosts h1 and h2. It also adds some switches and links.

- h1 (space) (command-name) -> command runs on the host h1

- net -> displays the network

- nautilus -> opens myComputer for the remote PC

- xterm h1 h2 -> opens two terminals for two hosts

- iperf -s -> starts server on that host

- iperf -c (ip-address-of-server) -> connects the client to the server

- iperf -s (ip-address-of-address) -u -> starts udp server

- firefox & -> firefox runs in background

- python -m SimpleHTTPServer -> starts *some* server *?*

- port -> localhost:8000

- cat \etc\hosts ->

- sudo emacs \etc\hosts -> opens emacs

- h1 ifconfig h1-eth0 11.0.0.1 -> change ip address

- net -> shows network

- **after changing for h2 too, we can ping**

- h2 ifconfig h2-eth0 netmask 255.255.0.0 -> change netmask

- **netmask and ip get added to get address.**

- **sub-netting**

# Notes Class - 2 (22-1-19)

- sudo mn --test iperf ->
  1. automated version of iperf commands
  2. it also tests the network by creating two hosts

- sudo mn --link tc,delay=10ms -> sets a delay of 10ms throughout the network

- sudo mn --link tc,bw=10 -> bw in Mbits/sec
- sudo mn --link tc,bw=10,delay=5ms
- tc has to do traffic shaping
- sudo mn --topo linear,3 -> sets topology as linear

  - h1 -- s1 -- s2 (-- h2) -- s3 -- h3
- sudo mn --topo linear,3 --link tc,delay=5ms            
- sudo mn --topo tree,depth=2,fanout=3

**Emacs notes**

- ctrl + X S -> save
- ctrl + X W
- ctrl X 2

# Notes Class 3 (29-1-18)

- ip address: 10.21.27.212
- lsof -i:1234
- kill -9 (pid)
- ip address for mail 10.50.0.88 port = 1234
- git remote add origin git@github.com:akash-07/class-notes-sem-6.git
