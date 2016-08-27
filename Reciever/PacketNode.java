public class PacketNode implements java.io.Serializable
{
	byte[] packet;
	int sequance;
	PacketNode(byte[] packet, int sequance)throws Exception
	{
		this.packet 	 = packet;
		this.sequance = sequance;
	}
	int getSequance()
	{
		return sequance;
	}
	byte[] getPacket()
	{
		return packet;
	}

}