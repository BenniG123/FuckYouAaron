package runnables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import lol.ComBuffer;

public class ServerRunnable implements Runnable {

	private ComBuffer buffer;
	private ServerSocket server;
	
	public ServerRunnable(ServerSocket s, ComBuffer b) {
		buffer = b;
		server = s;
	}

	@Override
	public void run() {
		Thread current = Thread.currentThread();
		while (true) {
			Socket client = null;
			BufferedReader in = null;
			PrintWriter out = null;
			try {
				System.out.println("Listening");
				client = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				in = new BufferedReader(new InputStreamReader(
						client.getInputStream()));
				out = new PrintWriter(client.getOutputStream(), true);
			} catch (IOException e) {
				System.out.println("Read failed");
				System.exit(-1);
			}

			buffer.lock(current);
			try {
				buffer.write(in.readLine(), current);
			} catch (IOException e) {
				e.printStackTrace();
			}
			buffer.unLock(current);
			out.print("Success");
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
