package it.polito.po.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import it.polito.oop.milliways.Hall;
import it.polito.oop.milliways.MilliwaysException;
import it.polito.oop.milliways.Party;
import it.polito.oop.milliways.Race;
import it.polito.oop.milliways.Restaurant;
import junit.framework.TestCase;

public class TestR5_Stats extends TestCase {

	Restaurant milliways;

	public void setUp() {
		milliways = new Restaurant();
	}

	public void testStatComposition() throws MilliwaysException {
		Hall h1 = milliways.defineHall(1);
		h1.addFacility("A");
		h1.addFacility("B");
		h1.addFacility("Z");

		Hall h2 = milliways.defineHall(2);
		h2.addFacility("A");
		h2.addFacility("B");
		h2.addFacility("C");

		Race r1 = milliways.defineRace("r1");
		r1.addRequirement("A");
		r1.addRequirement("B");

		Race r2 = milliways.defineRace("r2");
		r2.addRequirement("A");
		r2.addRequirement("B");
		r2.addRequirement("C");

		Map<Race, Integer> tm = new HashMap<>();

		Party p1 = milliways.createParty();
		p1.addCompanions(r1, 1);
		milliways.seat(p1, h1);
		tm.put(r1, 1);
		
		assertEquals("Wrong composition",tm,milliways.statComposition());

		Party p2 = milliways.createParty();
		p2.addCompanions(r1, 1);
		p2.addCompanions(r2, 23);
		tm.clear();
		tm.put(r2, 23);
		tm.put(r1, 2);
		
		assertFalse("Wrong composition",milliways.statComposition().equals(tm));
		
		milliways.seat(p2);
		assertEquals("Wrong composition",tm,milliways.statComposition());
	}

	public void testStatFacility() throws MilliwaysException {
		Hall h1 = milliways.defineHall(1);
		h1.addFacility("B");
		h1.addFacility("A");
		h1.addFacility("Z");
		Hall h2 = milliways.defineHall(2);
		h2.addFacility("B");
		h2.addFacility("A");
		h2.addFacility("C");
		assertEquals("Wrong number of facilities in stat list",4,milliways.statFacility().size());
		assertEquals("Wrong facility stat list order",Arrays.asList("A", "B", "C", "Z"),milliways.statFacility());

		Hall h3 = milliways.defineHall(3);
		h3.addFacility("Z");
		Hall h4 = milliways.defineHall(4);
		h4.addFacility("Z");
		assertEquals("Wrong facility stat list order",Arrays.asList("Z", "A", "B", "C"),milliways.statFacility());

		Hall h5 = milliways.defineHall(5);
		h4.addFacility("B");
		assertEquals("Wrong facility stat list order",Arrays.asList("B", "Z", "A", "C"),milliways.statFacility());
	}


    public void testStatHalls() throws MilliwaysException {
        Hall h1 = milliways.defineHall(10);
        h1.addFacility("B");
        h1.addFacility("A");
        Hall h2 = milliways.defineHall(12);
        h2.addFacility("H");
        h2.addFacility("J");
        
        Hall h3 = milliways.defineHall(3);
        h3.addFacility("Z");
        h3.addFacility("W");
        h3.addFacility("X");
        h3.addFacility("Y");
        
        assertEquals("Wrong halls stat","{2=[10, 12], 4=[3]}", milliways.statHalls().toString());
    }


}
