package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Race {
	private String name;
	private Set<String> requirements;

	Race(String name) throws MilliwaysException {
		this.name = name;
		this.requirements = new HashSet<>();
	}
	
	public void addRequirement(String requirement) throws MilliwaysException {
		if (requirements.contains(requirement))
			throw new MilliwaysException();
		requirements.add(requirement);
	}
	
	public List<String> getRequirements() {

//		MAkes no sense!!! Even if Eclipse suggested it
//		return (List<String>) requirements;
		
		List<String> lst = new ArrayList<>(requirements);
		//lst.sort((a, b) -> a.compareTo(b));
		Collections.sort(lst);
		return lst;
	}
	
	protected Set<String> _getRequirements() {
		return requirements;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
