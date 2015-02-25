package runnables;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class SetBackgroundRunnable implements Runnable {
	
	private String imageURL;
	private String imageName;
	
	public SetBackgroundRunnable(String inputURL, String name) {
		imageURL = inputURL.substring(11);
		imageName = name;
	}

	@Override
	public void run() {
		URL url = null;
		try {
			url = new URL(imageURL);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		InputStream in = null;
		try {
			in = new BufferedInputStream(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		OutputStream out = null;
		try {
			imageName = "C://" + imageName;
			out = new BufferedOutputStream(new FileOutputStream(imageName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			for ( int i; (i = in.read()) != -1; ) {
			    out.write(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Runtime.getRuntime().exec("cmd /c start changeDesktop.bat " + "\"" + imageName + "\"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
