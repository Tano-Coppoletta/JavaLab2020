package diet;

public class Intervallo {
	private Tempo inizio;
	private Tempo fine;
	
	public Intervallo(String in,String fin) {
		this.inizio=new Tempo(in);
		this.fine=new Tempo(fin);
	}
	
	public void setIN(String in) {
		inizio=new Tempo(in);
	}
	public void setFIN(String f) {
		fine=new Tempo(f);
	}
	public boolean contenuto(String cont) {
		if(inizio.compareTo(cont)<=0) {
			if(fine.compareTo(cont)>=0) {
				return true;
			}
		}
		return false;
	}
	public boolean successivo(String h) {
		if(inizio.compareTo(h)>0) {
			return true;
		}
		return false;
	}
	public String getIN() {
		return inizio.toString();
	}
}
