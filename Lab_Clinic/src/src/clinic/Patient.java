package clinic;

public class Patient {
	private String first;
	private String last;
	private String ssn;
	private Doctor doc;
	private boolean present=false;
	
	public Patient(String f,String l,String cod) {
		first=f;
		last=l;
		ssn=cod;
	}
	@Override
	public String toString() {
		return last+" "+first+" ("+ssn+")";
	}
	
	void addDoc(Doctor d) {
		doc=d;
		present=true;
	}
	
	public Doctor getDoc() {
		return doc;
	}
	
	public boolean isPresent() {
		return present;
	}
	
	public String getSsn() {
		return ssn;
	}
	public String getName() {
		return first;
	}
	
	public String getLast() {
		return last;
	}

}
