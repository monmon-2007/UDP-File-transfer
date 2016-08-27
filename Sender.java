import	java.io.*;
import	java.net.*;
import  java.nio.file.Files;
import  java.nio.file.Paths;
import 	java.util.*;

// UDP datagram broadcast test program

class Sender implements java.io.Serializable
{

	public static void main( String [] arg ) throws Exception
	{
		InetAddress			destination = InetAddress.getByName( "255.255.255.255" );
		DatagramPacket		sendPacket;
		MulticastSocket		socket = new MulticastSocket(3000);
		MulticastSocket		incomingConnection = new MulticastSocket(3001);
		MulticastSocket		socket2 = new MulticastSocket(8000);
		byte []				payload 		= new byte[8];
		DatagramPacket		receivePacket 	= new DatagramPacket( payload, payload.length );

		LinkedList<reciverNode> receiversAck = new LinkedList<reciverNode>();
		LinkedList<reciverNode> temp = new LinkedList<reciverNode>();



		//BufferedReader		stdIn = new BufferedReader( new InputStreamReader( System.in ) );
		int					port = 3000;
		String				s;
		String 				fileName;
		FileInputStream fStream = null;
		byte[] bfile = null;
		Scanner kb = new Scanner(System.in);

		System.out.print("please Enter the file name if its in the same folder, or the path if its in a different folder: " );
		fileName = kb.nextLine();
		System.out.println();

		System.out.print("Enter dropping percentage: %");
		int percent = kb.nextInt();
		System.out.println();


		try
		{
			File f = new File(fileName);
			bfile = new byte[(int) f.length()];
			fStream = new FileInputStream(f);
			fStream.read(bfile);
			fStream.close();
		}
		catch (Exception e){
			System.out.println("Please include a file");
		}

		System.out.println( "datagram target is " + destination + " port " + port );
		System.out.println( "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*" );
		socket.setBroadcast( true );
		byte end[]=new byte[104];


		//s="hello hello world I love avanilla Ice cream how about you son do you like the same?"; //simulates the File
			Node node = new Node(bfile);
			LinkedList<PacketNode> ls = new LinkedList<PacketNode>();


			for(int i=0; i<node.packetCount();i++)
			{
				ls.add(new PacketNode(node.getPackets().get(i),i));
			}


					int found=0;






				while(true)
				{
					try{

							incomingConnection.setSoTimeout(50);
							incomingConnection.receive( receivePacket );

					for(int g=0; g<receiversAck.size();g++)
					{
						if(receiversAck.get(g).getName().equals(new String(receivePacket.getData())) )
						found=1;
					}
					if(found==0)
					receiversAck.add(   new reciverNode(  new String(receivePacket.getData()),0) );
					found=0;

							sendPacket = new DatagramPacket( receivePacket.getData(),  receivePacket.getData().length, destination, 3002 );
							socket.send( sendPacket );

						}
						catch (SocketTimeoutException e)
						{
							break;
						}
				}

			System.out.println(receiversAck.size());


			Thread.sleep(2000);



			socket.setReuseAddress(true);
			for(int i=0; i<node.packetCount();i++)
			{


//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
				while(true)
				{
					try{

							incomingConnection.setSoTimeout(50);
							incomingConnection.receive( receivePacket );

					for(int g=0; g<receiversAck.size();g++)
					{
						if(receiversAck.get(g).getName().equals(new String(receivePacket.getData())) )
						found=1;
					}
					if(found==0)
					receiversAck.add(   new reciverNode(  new String(receivePacket.getData()),1) );
					found=0;

							sendPacket = new DatagramPacket( receivePacket.getData(),  receivePacket.getData().length, destination, 3002 );
							socket.send( sendPacket );

						}
						catch (SocketTimeoutException e)
						{
							break;
						}
				}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




				sendPacket = new DatagramPacket( serialize(ls.get(i)),  serialize(ls.get(i)).length, destination, port );
				socket.send( sendPacket );

				System.out.println("packet "+i+" is sent");
				Thread.sleep(500);
				int b = random();
				if (b > percent && b < 100){
				System.out.println("Sender droped Received Pakets...");
				System.out.println("Waiting...");
				//Thread.sleep(1000);
				}

				int flag=0;

				for(int f=0;f<receiversAck.size();f++)
					receiversAck.get(f).setX(0);


				while(true)
				{
					try{

							flag=0;
							socket2.setSoTimeout(1000);
							socket2.receive( receivePacket );
							System.out.println(new String(receivePacket.getData()) + "has Received the Packet");

							for(int g=0; g<receiversAck.size();g++)
							{
								if(receiversAck.get(g).getName().equals(new String(receivePacket.getData())) ){
								receiversAck.get(g).setX(1);

								}
							}

							for(int g=0; g<receiversAck.size();g++)
							{
								if(receiversAck.get(g).getX()==0)
								flag=1;

								//System.out.println(">>>>>>>"+flag);
								System.out.println(receiversAck.get(g).getName()+"    "+receiversAck.get(g).getX());
							}

							if(flag==0)
							break;




						}
						catch (SocketTimeoutException e)
						{
							socket.send( sendPacket );

							//If establish while sending


							//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
											while(true)
											{
												try{

														incomingConnection.setSoTimeout(50);
														incomingConnection.receive( receivePacket );

												for(int g=0; g<receiversAck.size();g++)
												{
													if(receiversAck.get(g).getName().equals(new String(receivePacket.getData())) )
													found=1;
												}
												if(found==0)
												receiversAck.add(   new reciverNode(  new String(receivePacket.getData()),1) );
												found=0;

														sendPacket = new DatagramPacket( receivePacket.getData(),  receivePacket.getData().length, destination, 3002 );
														socket.send( sendPacket );

													}
													catch (SocketTimeoutException bo)
													{
														break;
													}
											}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

							System.out.println("Resending packet "+i);


						}

				}



				System.out.println(new String(receivePacket.getData()));


				for(int g=0; g<temp.size();g++)
				{

					temp.get(g).setX(0);

				}



				Thread.sleep(2000);
			}
				//telling the reciever this is the end of File
				sendPacket = new DatagramPacket( end,  end.length, destination, port );
				socket.send( sendPacket );
				//*********************************************
				System.out.println("Sending process is completed!");

	}


	public static byte[] serialize(PacketNode obj) throws IOException
	{
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	public static int random(){
			int a = (int)Math.ceil(Math.random() * 100);
			return a;
	}

}