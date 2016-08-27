
import	java.io.*;
import	java.net.*;
import	java.math.*;
import  java.util.*;

public class receiver implements java.io.Serializable
{


	public static void main( String [] arg ) throws Exception
	{
		int percent = 0;

	//	try{
	//		percent = Integer.parseInt(arg[1]);
	//	} catch (Exception e){
	//		System.out.println("Please specify a percentage of packets dropped between 0 and 99");
	//	}
	//	if ((percent > 99) || (percent < 1)){
		//	System.out.println("not between 1 and 99");
		//}
		Scanner kb = new Scanner(System.in);
		String  path;

		System.out.print("Enter the Recieved File destination or the File Name: ");
		path =kb.nextLine();
		System.out.println();

		System.out.print("Enter Your Name: ");
		String str=kb.nextLine();
		System.out.println();

		System.out.print("Enter the Recieving percentage: %");
		percent=kb.nextInt();
		System.out.println();

		System.out.println("Now Waiting on the sender to send packets");

		MulticastSocket socket = new MulticastSocket(3000);
		MulticastSocket socket2 = new MulticastSocket(8000);
		MulticastSocket verify = new MulticastSocket(3002);


		DatagramPacket		sendPacket;
		int timeoutA=0;
		//File newfile = new File("test");
		//boolean bool = newfile.createNewFile();
		//System.out.println(bool);
		//String path = newfile.getAbsolutePath();
		int b;
		InetAddress			destination = InetAddress.getByName( "255.255.255.255" );
		byte []		payload = new byte[512];
		int occures=-1;
		DatagramPacket	receivePacket = new DatagramPacket( payload, payload.length );
		LinkedList<PacketNode> assemble = new LinkedList<PacketNode>();
		LinkedLists assemble2 = new LinkedLists();

		//let the server know we are receiving
		while(true){
		try{
		sendPacket = new DatagramPacket( str.getBytes(), str.length(), destination, 3001 ); // IP address change
		socket2.send( sendPacket ); // include in dropping

		verify.setSoTimeout(50);
		verify.receive( receivePacket );

		if(new String(receivePacket.getData()).contains(str))
		{
			System.out.println("**You Have Entered The Receiving Session**");
			break;
		}
		}
		catch (SocketTimeoutException e)
		{}
		}




		socket.setReuseAddress( true );
		int counter=0;
		for( ;; )
		{
			socket.receive( receivePacket ); // include in dropping
			socket.setReuseAddress( true );


			if(receivePacket.getLength()>103) 		//checks end of receive
			break;
			b = random();


			if (b > percent && b < 100){
				byte data[]=new byte[receivePacket.getLength()];
				System.arraycopy(receivePacket.getData(), receivePacket.getOffset(), data, 0, receivePacket.getLength());

			/*	if(    compareArrays(   assemble.get(assemble.size()),receivePacket.getData()   )      )
				{
					System.out.println("The packet has been Recieved already ");
					System.out.println("Here>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
				}
				*/
				//else
				//{
					PacketNode bb = (PacketNode)deserialize(data);


					if(assemble.size() ==0)
					{
						assemble.add(bb);
						System.out.println("packet Recieved  ");
						sendPacket = new DatagramPacket( str.getBytes(), str.length(), destination, 8000 ); // IP address change
						socket2.send( sendPacket ); // include in dropping
					}

					else if(assemble.get(assemble.size()-1).getSequance()== bb.getSequance())
					{
						System.out.println("packet has been already Recieved  ");
						timeoutA++;
						if(timeoutA==occures)
						{
							sendPacket = new DatagramPacket( str.getBytes(), str.length(), destination, 8000 ); // IP address change
							socket2.send( sendPacket ); // include in dropping
							timeoutA=0;
						}

					}

				else{
					assemble.add(bb);						//adding the data together in a customed linked list to be re assembled
					timeoutA=0;
					System.out.println("packet Recieved  ");

					sendPacket = new DatagramPacket( str.getBytes(), str.length(), destination, 8000 ); // IP address change
					socket2.send( sendPacket ); // include in dropping
				}

			}


		}

		for(int i=0; i<assemble.size(); i++)
		{
			assemble2.add(assemble.get(i).getPacket());
		}



		FileOutputStream stream = new FileOutputStream(path);
		try
		{
 		    stream.write(getValue(assemble2));
		} finally
		{
		    stream.close();
		}
		System.out.println("Recieved : New File");//print out this message

	}

	public static boolean compareArrays(byte[] array1, byte[] array2) {
	        boolean b = true;
	        if (array1 != null && array2 != null){
	          if (array1.length != array2.length)
	              b = false;
	          else
	              for (int i = 0; i < array2.length; i++) {
	                  if (array2[i] != array1[i]) {
	                      b = false;
	                  }
	            }
	        }else{
	          b = false;
	        }
	        return b;
    }


public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException
{
    ByteArrayInputStream in = new ByteArrayInputStream(data);
    ObjectInputStream is = new ObjectInputStream(in);
    return is.readObject();
}
//function getValue it takes the list of packets and re assemble them to one data to be processed
public static byte[] getValue(LinkedLists list)
{
	int count=0;
	for(int i=1;i<=list.size();i++)
	{
			count+=list.get(i).length;
	}

	byte[] temp = new byte[count];
	int count2=0;
	for(int i=1;i<=list.size();i++)
	{
		byte temp2[]=list.get(i);
		for(int j=0;j<list.get(i).length;j++)
		{
			temp[count2]=temp2[j];
			count2++;
		}
	}
	return temp;
}
	public static int random(){
		int a = (int)Math.ceil(Math.random() * 100);
		return a;
	}
}