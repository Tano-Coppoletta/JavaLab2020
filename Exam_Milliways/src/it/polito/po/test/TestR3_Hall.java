package it.polito.po.test;

import java.util.Arrays;

import it.polito.oop.milliways.Hall;
import it.polito.oop.milliways.MilliwaysException;
import it.polito.oop.milliways.Party;
import it.polito.oop.milliways.Race;
import it.polito.oop.milliways.Restaurant;
import junit.framework.TestCase;

public class TestR3_Hall extends TestCase {

	Restaurant milliways;

	public void setUp() {
		milliways = new Restaurant();
	}

	public void testCreateHall() throws MilliwaysException {
		Hall h1 = milliways.defineHall(1);
		Hall h2 = milliways.defineHall(2);
		
		assertNotNull("Hall not created",h1);
		assertNotNull("Hall not created",h2);
		assertNotSame("The same hall was returned twice",h1,h2);
		assertEquals("Wrong hall id",1,h1.getId());
        assertEquals("Wrong hall id",2,h2.getId());
	}

    public void testCreateHallDup() throws MilliwaysException {
        milliways.defineHall(2);
        try {
            milliways.defineHall(2);
            fail("Duplicate Hall not detected");
        } catch (MilliwaysException e) {
            // OK
        }
    }

	public void testAddGetFacility() throws MilliwaysException {
		Hall h1 = milliways.defineHall(1);
		Hall h2 = milliways.defineHall(2);

		h1.addFacility("A");
		h1.addFacility("C");
		h1.addFacility("B");
		h2.addFacility("A");

		assertEquals("Wrong hall facilities list",Arrays.asList("A", "B", "C"),h1.getFacilities());
        assertEquals("Wrong hall facilities list",Arrays.asList("A"),h2.getFacilities());

		try {
			h1.addFacility("C");
			fail("Duplicate Facility not detected");
		} catch (MilliwaysException e) {
			// OK
		}
	}

    public void testAddGetFacilityDup() throws MilliwaysException {
        Hall h1 = milliways.defineHall(1);
        h1.addFacility("A");
        h1.addFacility("C");

        try {
            h1.addFacility("C");
            fail("Duplicate Facility not detected");
        } catch (MilliwaysException e) {
            // OK
        }
    }
	public void testIsSuitable() throws MilliwaysException {
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
		assertTrue("Wrong suitability",h1.isSuitable(p1) );
		assertTrue("Wrong suitability",h2.isSuitable(p1) );
		
		p1.addCompanions(r2, 1);
		assertFalse("Wrong suitability",h1.isSuitable(p1) );
		assertTrue("Wrong suitability",h2.isSuitable(p1) );
	}
}
