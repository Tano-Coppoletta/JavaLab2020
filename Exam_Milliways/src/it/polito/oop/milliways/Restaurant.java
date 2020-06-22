package it.polito.oop.milliways;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.HashMap;
import static java.util.stream.Collectors.*;
import java.util.Collections;
import static java.util.Comparator.*;

public class Restaurant {
	private List<Hall> halls;
	private List<Party> seatedParty;
	
	private Map<String,Race> races = new HashMap<>();
	private Map<Integer,Hall> hallsm = new HashMap<>();

	public Restaurant() {
		halls = new LinkedList<>();
		seatedParty = new LinkedList<>();
	}
	
	public Race defineRace(String name) throws MilliwaysException{
	    if(races.containsKey(name)) throw new MilliwaysException();
	    Race r = new Race(name);
	    races.put(name, r);
	    return r;
	}
	
	public Party createParty() {
	    return new Party();
	}
	
	public Hall defineHall(int id) throws MilliwaysException{
	    if(hallsm.containsKey(id)) throw new MilliwaysException();
	    
	    Hall hall = new Hall(id);
	    hallsm.put(id,hall);
	    halls.add(hall);
	    return hall;
	}

	public List<Hall> getHallList() {
		return halls;
	}

	public Hall seat(Party party, Hall hall) throws MilliwaysException {
		if (!hall.isSuitable(party))
			throw new MilliwaysException();
		
		seatedParty.add(party);
		return hall;
	}

	public Hall seat(Party party) throws MilliwaysException {
		for (Hall hall : halls)
			if (hall.isSuitable(party))
				return seat(party, hall);
		
		throw new MilliwaysException();
	}

	public Map<Race, Integer> statComposition() {
		return seatedParty.stream()
				.flatMap(e -> e.getComposition().entrySet().stream())
				.collect(Collectors.groupingBy(
						Map.Entry<Race, Integer>::getKey,
						Collectors.summingInt(Map.Entry::getValue)));
	}

	public List<String> statFacility() {
		Comparator<Map.Entry<String,Long>> c = 
				comparing(Map.Entry::getValue, 
						  reverseOrder());
		c = c.thenComparing(Map.Entry::getKey);
		
		return halls.stream().flatMap(f -> f.getFacilities().stream())
				.collect(Collectors.groupingBy(
						x -> x, 
						Collectors.counting()))
				.entrySet().stream()
				// leverages the "stability" of sorting algorithm
//				.sorted(comparing(Map.Entry::getKey))
//				.sorted(comparing(Map.Entry::getValue, reverseOrder())).map(Entry::getKey)
				
				// OR
				
				// uses 'thenComparing()'  that requires explicit typing of comparing argument
				.sorted(comparing(Map.Entry<String,Long>::getValue, 
											 reverseOrder()).
								   thenComparing(Map.Entry::getKey))
				// OR
				
				// use a comparator defined separately
//				.sorted(c)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
	
	public Map<Integer,List<Integer>> statHalls() {
//	    return halls.stream().
//	            sorted(comparingInt(Hall::getId)).
//	            collect(groupingBy(
//                                Hall::getNumFacilities,
//                                TreeMap::new,
//                                mapping(Hall::getId, toList())
//                                ));

		// OR
		
	    return halls.stream().
	            collect(groupingBy(
                                Hall::getNumFacilities,
                                TreeMap::new,
                                mapping(Hall::getId, 
										collectingAndThen(toList(),
                										l->{
                											Collections.sort(l);
                											return l;
                										}))
                                ));
	
	}

}
