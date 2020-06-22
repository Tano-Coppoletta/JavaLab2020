package it.polito.po.test;

import java.util.Arrays;

import it.polito.oop.milliways.Hall;
import it.polito.oop.milliways.MilliwaysException;
import it.polito.oop.milliways.Party;
import it.polito.oop.milliways.Race;
import it.polito.oop.milliways.Restaurant;
import junit.framework.TestCase;

public class TestR4_Restaurant extends TestCase {

	Restaurant milliways;

	public void setUp() {
		milliways = new Restaurant();
	}

	public void testGetHallList() throws MilliwaysException {
		Hall h1 = milliways.defineHall(1);
		Hall h2 = milliways.defineHall(3);
		Hall h3 = milliways.defineHall(2);

		assertEquals("Wrong halls list",Arrays.asList(h1, h2, h3),milliways.getHallList());
	}

	public void testSeat() throws MilliwaysException {
		Hall h1 = milliways.defineHall(1);
		Hall h2 = milliways.defineHall(2);

		h1.addFacility("A");
		h1.addFacility("B");
		h1.addFacility("Z");
		h2.addFacility("A");
		h2.addFacility("B");
		h2.addFacility("C");

		Race r1 = milliways.defineRace("r1");
		Race r2 = milliways.defineRace("r2");
		r1.addRequirement("A");
		r2.addRequirement("A");
		r1.addRequirement("B");
		r2.addRequirement("B");
		r2.addRequirement("C");

		Party p1 = milliways.createParty();
		p1.addCompanions(r1, 1);
		assertSame("Wrong hall returned",h1,milliways.seat(p1, h1));
		
		p1.addCompanions(r2, 1);
		try {
			milliways.seat(p1, h1);
			fail("Unsuitability not detected (P1 requires C)");
		} catch (MilliwaysException e) {
			// OK
		}
		assertEquals("Wrong hall selected", h2, milliways.seat(p1));
	}

	   public void testSeat2() throws MilliwaysException {
	        Hall h1 = milliways.defineHall(1);
	        Hall h2 = milliways.defineHall(2);

	        h1.addFacility("A");
	        h1.addFacility("B");
	        h1.addFacility("Z");
	        h2.addFacility("A");
	        h2.addFacility("B");
	        h2.addFacility("C");

	        Race r1 = milliways.defineRace("r1");
	        Race r2 = milliways.defineRace("r2");
	        r1.addRequirement("A");
	        r2.addRequirement("A");
	        r1.addRequirement("B");
	        r2.addRequirement("B");
	        r2.addRequirement("C");

	        Party p1 = milliways.createParty();
	        p1.addCompanions(r1, 1);
	        p1.addCompanions(r2, 1);

	        assertEquals("Wrong hall selected", h2, milliways.seat(p1));
	    }
}
