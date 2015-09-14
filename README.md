# Distributed_Bitcoin_Mining_System
This is an distributed Bitcoin mining system based on Akka


How to run

The bitcoin mining system consists of two part: the server part and the client part. On the server machine, we can open the folder of Server and typing in Terminal :

 sbt “run  <gator_id > <number of zero>”

Then we can get the information of server, especially the ip address of server which we need to use on client machine.

In this example we can see that the IP address of server is 192.168.0.101

On the client machine, we can open the folder of Client and typing in Terminal :
If we need the client to execute in a certain duration time : 

 run <server IP> <server port> <num of actor > <sec of duration>

If we need the client to execute without an end:

 run <server IP> <server port> <num of actor >



running result

I give the running result with the gator_id=”scy0208” and zero number equals to 4



Here is some of the result:
Receive Bitcoin: 0000c3f171d6a41d47487ba631c507991cf53790da07971546742f7474266630  With code: scy0208akka.actor.ActorCell@792b6c19HBuL9OYAOT7nqsL9
Receive Bitcoin: 00001539166c9bbe9f67f520dcc97d9074a303534a49014771ebb61357ccce66  With code: scy0208akka.actor.ActorCell@19d9f3f3nD8S1mH4R8ODMhwF
Receive Bitcoin: 00009cf4d0730e6ef31d8365b08b48dbb0dd66dd357affc2e2b76157dedbee54  With code: scy0208akka.actor.ActorCell@6bea2475X5dqCy1pd3wM6Fcg
Receive Bitcoin: 000033e3bc1f580f443b54536b0c1d7ffebcf99f9d64c900b845ed3c6b200b08  With code: scy0208akka.actor.ActorCell@6bea2475xi1RcWXEwZqdn9gq
Receive Bitcoin: 000022bc0ebaadaec362d17fab93bf08c9ddd68fec34620e9b9101c6e2f3bfa3  With code: scy0208akka.actor.ActorCell@6bea24751i8xXoLZcmlKJzjL
Receive Bitcoin: 000050bd6bfd586de6429f41324b11469599dee4bfc8b6fe766fcaf216152161  With code: scy0208akka.actor.ActorCell@792b6c19nCfJhsw3fuTaaEeC
Receive Bitcoin: 000062ce145027c5e1ddeb8fe3a1698871e265028ed782936581abef5ee6eb39  With code: scy0208akka.actor.ActorCell@6d821763d06VdRxNFTChL0zs
Receive Bitcoin: 00005a978c9f7afcb7de1a6c5a78ec32d6cd0e26e2028e8476750cc16cea8e79  With code: scy0208akka.actor.ActorCell@5cdbd5ce9PR7aIWjfjexeugK
Receive Bitcoin: 00009fa8ea5968ab6eec01eaedce226fd1c3275f4ad504620042044090657f70  With code: scy0208akka.actor.ActorCell@792b6c19LuyDncr8ysVE4X91
Receive Bitcoin: 0000eefaa852f5c3454e4aa40595c0ea1abf9f8525702769ab3fecf94cce3668  With code: scy0208akka.actor.ActorCell@792b6c19sTQo8oJbwa4WWWT0
Receive Bitcoin: 00000178a0007912a137355ce146f6e5fe331cb68995b8755a8769f5fe7b89a9  With code: scy0208akka.actor.ActorCell@19d9f3f3jAaPcRCM2bShseyN
Receive Bitcoin: 000047db1a5eceb7f736add84027967d6598bf8249fea12dde0abd354a0bf077  With code: scy0208akka.actor.ActorCell@5cdbd5ceHTHKiGB6XyPM3hFF
Receive Bitcoin: 0000bd95a1a6806b58a9c5f4070f6c4e6e08d90811f5d247b72ac3cf2539fc98  With code: scy0208akka.actor.ActorCell@6d821763Is87GiffefYoZaHN
Receive Bitcoin: 00008d068da4c89fde0efa008841f4609b547ec96bc5247f2b2b54c1daec8ec3  With code: scy0208akka.actor.ActorCell@19d9f3f3SFIXXzXa0B4SV1Lh
Receive Bitcoin: 00003d51654736e088a199bb8a7818ee577fa512e774ea1980050d5dcfddb604  With code: scy0208akka.actor.ActorCell@6d821763TO4clFp1jm98YGHo
Receive Bitcoin: 000029927d1f3d7b1b947a2eeec3480e274d4c7b81881e4a57cbb8510161c0ff  With code: scy0208akka.actor.ActorCell@5cdbd5cemEl8kdfdSt1DR48G
Receive Bitcoin: 0000cfdbcc761d8d0690f5da5a07e7f3cf43b6f5e16e590b2fa23b185b7da6cb  With code: scy0208akka.actor.ActorCell@5cdbd5ceYsiJktnodDo8dZvW
The performance of multi-actor performance and mining efficiency

Experiment of one client node execute the program to find bitcoin with 4 zero, Actor system duration time is 30s. The command is: 

 time sbt “run <server IP> <server port> <num of actor > <sec of duration>”

The client is a 2 core 4 thread CPU with the frequency of 2.53GHz, operating system is Ubunto 14.04, Memory size is 5863MB.

The unit of time is second.
num of actor	real time	user time	sys time	num of bitcoin	Bitcoins/real time	CPU time/ real time
1	38.417	11.084	0.495	0	0	0.301403025
2	38.758	11.314	0.402	0	0	0.30228598
3	38.382	11.152	0.43	0	0	0.301756031
4	38.428	11.142	0.513	0	0	0.303294473
5	49.055	83.406	1.013	21	0.428090918	1.720905107
6	44.272	76.166	1.205	11	0.24846404	1.747628298
7	54.988	92.815	1.216	13	0.236415218	1.710027642
8	149.949	284.327	2.334	23	0.153385484	1.911723319
9	123.194	231.293	2.67	18	0.146111012	1.899142815
10	194.282	370.269	3.34	39	0.200739132	1.923024264
11	194.32	367.599	4.023	44	0.22643063	1.912422808
12	304.574	583.276	5.033	54	0.177296815	1.931579846
13	194.144	368.039	3.468	29	0.149373661	1.913564159

From the experiment data we can see that when the actor number is 5, the system satisfied with highest speed of bitcoin mining, when the actor number is 12, the CPU utilize rate is highest, which means the parallel performance is the best.

The Bitcoin with most zero
The bitcoin with most zero is:
Receive Bitcoin: 0000000077f8b74b194d2c75f11f993da5d6b3c10c6283fad3c21bdba401539c  With code: scy0208akka.actor.ActorCell@46b9375blwfEZLPGCa2rBavI

With 8 zero.
The highest number of machines running simultaneously

I did the experiment with 6 machines in LAN runing simultaniously, it performance good.
