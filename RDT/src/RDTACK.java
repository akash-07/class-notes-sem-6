import java.io.Serializable;


public class RDTACK implements Serializable{
	
	private int seq;

	public RDTACK(int seq) {
		super();
		this.seq = seq;
	}

	public int getSequenceNumber() {
		return seq;
	}

	public void setSequenceNumber(int packet) {
		this.seq = seq;
	}
	
	

}