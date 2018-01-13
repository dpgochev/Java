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

			PrintWriter outToServer = new PrintWriter(sock.getOutputStream(), true);

			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));

			while (true) {

				FromServer = inFromServer.readLine();

				if (FromServer.equals("quit") ) {
					sock.close();
					break;
				} else {
					System.out.println("RECIEVED:" + FromServer);
					System.out.println("SEND(Type Q or q to Quit):");

					ToServer = inFromUser.readLine();

					if (ToServer.equals("Q") || ToServer.equals("q")) {
						outToServer.println(ToServer);
						sock.close();
						break;
					} else {
						outToServer.println(ToServer);
					}
				}

			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
