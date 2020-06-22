package clinic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Doctor extends Patient{
	private int docID;
	private String specializzazione;
	private List<Patient> pazienti=new ArrayList<>();
	
	public Doctor(String f,String l,String ssn,int b,String spec) {
		super(f,l,ssn);
		docID=b;
		specializzazione=spec;
	}
	
	@Override
	public String toString() {
		return super.toString()+"["+docID+"]"+": "+specializzazione;
	}
	
	public String getSpec() {
		return specializzazione;
	}
	
	public void addPatient(Patient p) {
		pazienti.add(p);
	}
	
	public int getID() {
		return docID;
	}
	public List<String> getLista() {
		return pazienti.stream().map(Patient::getSsn).
		collect(Collectors.toList());
	}
	
	public int getSize() {
		return pazienti.size();
	}
	public String bella() {
		String s =String.format("%3d", pazienti.size());
		return s+" : "+docID+ " "+this.getLast()+" "+ this.getName();
	}
}
	

