package it.polito.po.test;

import java.util.Arrays;

import it.polito.oop.milliways.MilliwaysException;
import it.polito.oop.milliways.Party;
import it.polito.oop.milliways.Race;
import it.polito.oop.milliways.Restaurant;
import junit.framework.TestCase;

public class TestR2_Party extends TestCase {

	Restaurant milliways;

	public void setUp() {
		milliways = new Restaurant();
	}

	public void testCreateParty() throws MilliwaysException {
		Party p = milliways.createParty();
		
		assertNotNull("Party was not created",p);
	}

	public void testAddCompanion() throws MilliwaysException {
		Race r1 = milliways.defineRace("r1");
		Race r2 = milliways.defineRace("r2");

        Party p0 = milliways.createParty();
		Party p1 = milliways.createParty();
		Party p2 = milliways.createParty();

		p1.addCompanions(r1, 1);
		p1.addCompanions(r2, 2);
		p2.addCompanions(r1, 2);
		
        assertTrue("Party should be empty", p0.getNum() == 0);
		assertTrue("Companions not added", p1.getNum() > 0);
        assertTrue("Companions not added", p2.getNum() > 0);
	}

	public void testGetNum() throws MilliwaysException {
		Race r1 = milliways.defineRace("r1");
		Race r2 = milliways.defineRace("r2");
		Party p1 = milliways.createParty();

		int t = 5;
		p1.addCompanions(r1, t);
		p1.addCompanions(r2, 2 * t);
		assertEquals("Wrong number of companions",t,p1.getNum(r1));
		assertEquals("Wrong number of companions",2*t,p1.getNum(r2));
	}

	public void testGetRequirements() throws MilliwaysException {
		Race r1 = milliways.defineRace("r1");
		Race r2 = milliways.defineRace("r2");
		Race r3 = milliways.defineRace("r3");

		Party p1 = milliways.createParty();
		
		r1.addRequirement("B");
		r2.addRequirement("A");
		r2.addRequirement("C");
		r3.addRequirement("0");
		r3.addRequirement("Z");
		
		p1.addCompanions(r1, 1);
		p1.addCompanions(r2, 1);
		assertEquals("Wrong requirements list",Arrays.asList("A", "B", "C"),p1.getRequirements());
		p1.addCompanions(r2, 99);
		assertEquals("Wrong requirements list",Arrays.asList("A", "B", "C"),p1.getRequirements());
		p1.addCompanions(r3, 1);
		assertEquals("Wrong requirements list",Arrays.asList("0", "A", "B", "C", "Z"),p1.getRequirements());
	}
}
