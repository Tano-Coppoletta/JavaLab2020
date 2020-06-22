package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hall {
	private int id;
	private Set<String> facilities;

    Hall(int id) {
		facilities = new HashSet<>();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void addFacility(String facility) throws MilliwaysException {
		if (facilities.contains(facility))
			throw new MilliwaysException();
		
		facilities.add(facility);
	}

	public List<String> getFacilities() {
		List<String> lst = new ArrayList<>(facilities);
		Collections.sort(lst);
		return lst;
	}
	
	int getNumFacilities(){
	    return facilities.size();
	}

	protected Set<String> _getFacilities() {
		return facilities;
	}

	public boolean isSuitable(Party party) {
//		for (String req : party.getRequirements())
//			if (!facilities.contains(req))
//				return false;
//		return true;
		
		return party.getRequirements().stream()
				.allMatch(r -> facilities.contains(r));
	}

	@Override
	public String toString() {
		return "Hall [id=" + id + ", facilities=" + getFacilities() + "]";
	}

}
