package runnables;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class OpenUrlRunnable implements Runnable {

	private String url;

	public OpenUrlRunnable(String inputURL) {
		url = inputURL.substring(inputURL.indexOf("www"));
		System.out.println("url = " + url);
	}

	@Override
	public void run() {
		// Create Desktop object
		Desktop d = Desktop.getDesktop();

		// Browse a URL, for example www.facebook.com
		try {
			d.browse(new URI(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
