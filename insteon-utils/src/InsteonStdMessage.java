
public class InsteonStdMessage {
	private byte[] msg = new byte[11];
	private byte msgCommand;
	private byte msgFromHigh;
	private byte msgFromMid;
	private byte msgFromLow;
	private byte msgToHigh;
	private byte msgToMid;
	private byte msgToLow;
	private byte msgFlags;
	private byte msgCmd1;
	private byte msgCmd2;
	
	public InsteonStdMessage(byte[] buf) {
		this.msg = buf;
		this.msgCommand = buf[1];
		this.msgFromHigh = buf[2];
		this.msgFromMid = buf[3];
		this.msgFromLow = buf[4];
		this.msgToHigh = buf[5];
		this.msgToMid = buf[6];
		this.msgToLow = buf[7];
		this.msgFlags = buf[8];
		this.msgCmd1 = buf[9];
		this.msgCmd2 = buf[10];
	}
	
	public void printRawMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%02X ", InsteonMessageConstants.STX));
		sb.append(String.format("%02X ", msgCommand));
		sb.append(String.format("%02X ", msgFromHigh));
		sb.append(String.format("%02X ", msgFromMid));
		sb.append(String.format("%02X ", msgFromLow));
		sb.append(String.format("%02X ", msgToHigh));
		sb.append(String.format("%02X ", msgToMid));
		sb.append(String.format("%02X ", msgToLow));
		sb.append(String.format("%02X ", msgFlags));
		sb.append(String.format("%02X ", msgCmd1));
		sb.append(String.format("%02X ", msgCmd2));
		System.out.println("RX: " + sb.toString());
	}
	
	public void printDecodedMessage() {
		printRawMessage();
		StringBuilder sb = new StringBuilder();
		sb.append("\tCommand: ");
		sb.append(String.format("%02X ",  msgCommand));
		System.out.println(sb.toString() + "(" + InsteonMessageConstants.REC_MSG_NAMES.get(msgCommand) +")");
		sb = new StringBuilder();
		sb.append(String.format("%02X", msgFromHigh));
		sb.append(".");
		sb.append(String.format("%02X", msgFromMid));
		sb.append(".");
		sb.append(String.format("%02X", msgFromLow));
		System.out.println("\tFrom: " + sb.toString());
		sb = new StringBuilder();
		sb.append(String.format("%02X", msgToHigh));
		sb.append(".");
		sb.append(String.format("%02X", msgToMid));
		sb.append(".");
		sb.append(String.format("%02X", msgToLow));
		System.out.println("\tTo: " + sb.toString());
		InsteonStdMsgFlags flags = new InsteonStdMsgFlags((int)msgFlags);
		System.out.println(flags.toString());
		System.out.println("\tCmd1: " + String.format("%02X ", msgCmd1) + "(" + InsteonMessageConstants.REC_SD_CMD1_NAMES.get(msgCmd1) + ")");
		
	}
}
