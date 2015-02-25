package lol;

public class ComBuffer {

	private String buffer;
	private Boolean inUse;
	private Thread accessor;

	public ComBuffer() {
		buffer = "";
		inUse = false;
	}

	public Boolean lock(Thread t) {
		//Block
		while(inUse); 
		inUse = true;
		accessor = t;
		return true;

	}

	public Boolean read(String output, Thread t) {
		if (inUse && t.equals(accessor)) {
			output = buffer;
			//Consume output
			buffer = null;
			return true;
		} else {
			return false;
		}
	}

	public Boolean unLock(Thread t) {
		if (inUse && accessor.equals(t)) {
			inUse = false;
			accessor = null;
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean write(String input, Thread t) {
		if (inUse && t.equals(accessor)) {
			buffer = input;
			return true;
		} else {
			return false;
		}
	}

}
