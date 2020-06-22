package transactions;

import java.util.ArrayList;
import java.util.List;

public class Carrier {
	private String name;
	private List<Region> regioni;
	
	public Carrier(String name) {
		this.name=name;
		regioni=new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public List<Region> getRegions(){
		if(regioni!=null)
			return regioni;
		return null;
	}
	
	public void addRegion(Region r) {
		regioni.add(r);
	}
	
	public boolean containRegion(Region r) {
		return regioni.contains(r);
	}
}
