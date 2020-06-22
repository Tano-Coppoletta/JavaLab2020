package transactions;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Region {
	private String name;
	private Set<Place> place=new HashSet<>();
	private List<Carrier> carriers=new ArrayList<>();
	
	public Region(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	
	
	public boolean isPresent(String s) {
		if(place.contains(s)) 
			return true;
		return false;
	}
	
	public void addPlace(Place p) {
		place.add(p);
	}
	
	public void addCarrier(Carrier c) {
		carriers.add(c);
	}
	public List<Carrier> getCarriers(){
		return carriers;
	}
}
