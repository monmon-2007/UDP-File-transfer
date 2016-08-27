import java.util.*;

public class Node
{
	final int packetMax=20;
	byte data[];
	LinkedList<byte[]> packets= new LinkedList<byte[]>();

	public Node(byte dataA[])
	{
		this.data=dataA;
		int dataLength = dataA.length;
		double d=dataLength;
		int dataSize = data.length;
		double packetCount = d/20;
		int ByteCounter = 0;
		byte temp[];

		int start=0;
		int currentsize=0;

			if(data.length<=packetMax)
			{
				packets.add(dataA);

			}
			else//if(data.length>packetMax)
			{
				//int g=packetCount;
				//if(packetCount%1!=0)
				//packetCount++;
//datadataSize=1;
int kara=0;

				for(double i=0; i<packetCount;i++)//number of packets needed
				{


						if((dataSize-kara)>=packetMax)
						{
							currentsize=packetMax;
							kara+=packetMax;

						}
						else
						currentsize=dataSize-kara;

					packets.add(copyMe(data,currentsize,start));
					start+=currentsize;

				}

			}


}

	public int packetCount()
	{
		return packets.size();
	}

	public byte[] getPacket(int i)
	{
		return packets.get(i);
	}

	public LinkedList<byte[]> getPackets()
	{
		return packets;
	}

	public int getDataSize()
	{
		return data.length;
	}
	public static byte[] copyMe(byte data[], int newSize,int from)
	{
		byte temp[] = new byte[newSize];
		int from1=from;

		for(int i=0; i<newSize; i++)
		{
			temp[i]= data[from1];
			from1++;
		}
		return temp;
	}

}