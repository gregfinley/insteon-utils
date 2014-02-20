import java.net.InetAddress;
import java.net.*;
import java.io.*;


public class InsteonCLI {

	public void run() {
			try {
				byte b;
				int serverPort = 9761;
				InetAddress host = InetAddress.getByName("10.0.0.145");
				
				InputStream in;
				OutputStream out;
				
				Socket socket = new Socket(host, serverPort);
				System.out.println("Connected to Insteon Hub at: " + socket.getRemoteSocketAddress());
				
				in = socket.getInputStream();
				out = socket.getOutputStream();
				
				while (true) {
					// Wait for STX
					while ((b = readByte(in)) != 0x02) {
						System.out.println("Ignoring non STX byte: " + b);
					}
					// Read command
					byte c = readByte(in);
					
					// 
					if (c == 0x50) {
						byte[] msgBuf = new byte[InsteonMessageConstants.REC_MSG_SIZES.get(c)];
						msgBuf[0] = InsteonMessageConstants.STX;
						msgBuf[1] = c;
						fillBuffer(in, msgBuf, 2);
						InsteonStdMessage iStdMsg = new InsteonStdMessage(msgBuf);
						iStdMsg.printDecodedMessage();
					}
				}
				
				
				
			} catch (IOException e) {
					e.printStackTrace();
			}
			
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InsteonCLI insteon = new InsteonCLI();
		insteon.run();
	}

	private static void fillBuffer(InputStream in, byte[] buf, int off) throws IOException {
		while (off < buf.length) {
			int numRead = in.read(buf, off, buf.length - off);
			if (numRead == -1) {
				throw new IOException("Unexpected end of stream");
			}
			off += numRead;
		}
	}
	
	
	private static byte readByte(InputStream in) throws IOException {
		int v = in.read();
		if (v == -1) {
			throw new IOException("Unexpected end of stream");
		}
		return (byte)v;
	}
}
