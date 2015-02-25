package lol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

import runnables.OpenUrlRunnable;
import runnables.ServerRunnable;
import runnables.SetBackgroundRunnable;

public class Run {

	public static void main(String[] args) throws IOException,
			URISyntaxException, InterruptedException {
		// Starts at startup
		// Listens on server runnable thread

		String line = null;
		while (true) {
			ServerSocket server = new ServerSocket(2048);
			System.out.println("Listening");
			Socket client = server.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			if(parseLine(line, client)) {
				//Send back confirmation
				out.println("Success");
			} else {
				// back negative
				out.println("Failure");
			}
			client.close();
		}
	}
	
	private static boolean parseLine(String input, Socket client) throws IOException {
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		if(input == null) {
			out.println("null");
			return false;
		}
		if (input.contains("url")) {
			Thread t = new Thread(new OpenUrlRunnable(input));
			t.start();
			return true;
		} else if(input.contains("background")) {
			int index = input.lastIndexOf("/") + 1;
			String name = input.substring(index);
			Thread t = new Thread(new SetBackgroundRunnable(input, name));
			t.start();
			return true;
		} else {
			out.println("unknown");
			return false;
		}
	}

}
