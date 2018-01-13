package bg.uni.sofia.fmi.echo.server.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {
	public static void main(String[] args) {
		String FromServer;
		String ToServer;
		try {

			Socket sock = new Socket("localhost", 4444);

			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

			PrintWriter outToServer = new PrintWriter(sock.getOutputStream());

			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));

			while (true) {
				ToServer = inFromUser.readLine();
				
				 
				outToServer.println(ToServer);
outToServer.flush();
				
				FromServer = inFromServer.readLine();

				if (FromServer.equals("quit")) {
					sock.close();
					break;
				} else {
					
					System.out.println("RECIEVED:" + FromServer);
					System.out.println("SEND(Type Q or q to Quit):");

					 

					if (ToServer.equals("quit")) {
						outToServer.println(ToServer);
						sock.close();
					break;
					} else {
						outToServer.println(ToServer);
					}
				}

			}
			inFromUser.close();
			outToServer.close();
			inFromServer.close();

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("out of loop");

	}
}
