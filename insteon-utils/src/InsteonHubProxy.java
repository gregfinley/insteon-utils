import java.net.*;
import java.io.*;

public class InsteonHubProxy {
	private static String downstreamHost = "";
	private static int downstreamPort;

	public void run() {
		try {
			// Create a ServerSocket to receive data
			ServerSocket ss = new ServerSocket(9761);
			
			// Create buffers for client-to-server and server-to-client communication.
			// We make one final so it can be use in an anonymous class below
			
			
		} catch (Exception e) {
			System.out.println("Uh Oh!");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if ((args[0].length() == 0) || (args[1].length() == 0)) {
			System.out.println("Usage: <downstreamHost> <downstreamPort>");
			System.exit(-1);
		}
		

	}

}
