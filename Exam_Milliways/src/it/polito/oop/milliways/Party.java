package it.polito.oop.milliways;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

public class Party {
	private Map<Race, Integer> composition;

	protected Map<Race, Integer> getComposition() {
		return composition;
	}

	Party() {
		composition = new HashMap<>();
	}

	public void addCompanions(Race race, int num) {
		System.err.println(race);
		int old_num = 0;
		if (composition.containsKey(race))
			old_num = composition.get(race);
		composition.put(race, old_num + num);
	}

	public int getNum() {
//		return composition.entrySet().stream()
//				.collect(Collectors.summingInt(Map.Entry::getValue));
		
		// OR
		
		return composition.values().stream()
				.mapToInt(i->i)
				.sum();

	}

	public int getNum(Race race) {
		if (composition.containsKey(race))
			return composition.get(race);
		else
			return 0;
	}

	public List<String> getRequirements() {
//		Set<String> req = new TreeSet<>();
//		for (Race r : composition.keySet())
//			req.addAll(r._getRequirements());
//		return new ArrayList<String>(req);

		//		OR
		return
		composition.keySet().stream()
		.flatMap(r -> r._getRequirements().stream())
		.distinct()
		.sorted()
		.collect(toList());
	}
	
	public Map<String,Integer> getDescription(){
	    return composition.entrySet().stream().
	            collect(toMap(e->e.getKey().getName(),
	            			  e->e.getValue()))
//	                          Map.Entry::getValue))
	            ;
	}
 
	@Override
	public String toString() {
		return "Party [composition=" + composition + "]";
	}
}
