
public class InsteonStdMsgFlags {
	private final int bits;
	private final boolean ack;
	private final boolean broadcast;
	private final boolean extended;
	private final boolean group;
	
	public InsteonStdMsgFlags(int bits) {
		this.bits = bits;
		ack = checkBit(bits, InsteonMessageConstants.STD_FLAG_BIT_ACK);
		broadcast = checkBit(bits, InsteonMessageConstants.STD_FLAG_BIT_BC_OR_NAK);
		extended = checkBit(bits, InsteonMessageConstants.STD_FLAG_BIT_EXT);
		group = checkBit(bits, InsteonMessageConstants.STD_FLAG_BIT_GROUP);
	}
	
	public boolean isAck() {
		return ack;
	}
	
	public boolean isBroadcast() {
		return broadcast;
	}
	
	public boolean isExtended() {
		return extended;
	}
	
	public boolean isGroup() {
		return group;
	}
	
	public String toString() {
		Long lBits = new Long(bits);
		
		return "\tFlags: " + Long.toBinaryString(lBits) + "\n\t\tAck: " + String.valueOf(ack) + "\n\t\tBroadcast: " + String.valueOf(broadcast) + "\n\t\tExtended: " + String.valueOf(extended) + "\n\t\tGroup: " + String.valueOf(group); 
	}
	private static boolean checkBit(int value, int idx) {
		return (value & (1 << idx)) != 0;
	}
}
