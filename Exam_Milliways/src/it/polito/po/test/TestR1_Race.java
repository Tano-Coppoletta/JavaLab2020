package it.polito.po.test;

import java.util.Arrays;
import java.util.List;

import it.polito.oop.milliways.MilliwaysException;
import it.polito.oop.milliways.Race;
import it.polito.oop.milliways.Restaurant;
import junit.framework.TestCase;

public class TestR1_Race extends TestCase {

	Restaurant milliways;

	public void setUp() {
		milliways = new Restaurant();
	}

	public void testRace() throws MilliwaysException {
		Race r1 = milliways.defineRace("r1a");
		Race r2 = milliways.defineRace("r2a");
		assertEquals("Wrong name for race", "r2a",r2.getName());
		assertEquals("Wrong name for race", "r1a",r1.getName());
	}

	public void testRace2() throws MilliwaysException {
		milliways.defineRace("r_fail");
		try {
			milliways.defineRace("r_fail");
			fail("Duplicate Race name not detected");
		} catch (MilliwaysException e) {
			// OK
		}
	}

	public void testAddRequirement() throws MilliwaysException {
		Race r1 = milliways.defineRace("r1b");
		Race r2 = milliways.defineRace("r2b");
		r1.addRequirement("Foo");
		r1.addRequirement("Bar");
		r2.addRequirement("Bar");
		r2.addRequirement("Foo");
		r2.addRequirement("Baz");
		assertEquals("Wrong number of requirements",2,r1.getRequirements().size());
        assertEquals("Wrong number of requirements",3,r2.getRequirements().size());
	}

	public void testAddRequirement2() throws MilliwaysException {
		Race r1 = milliways.defineRace("r1c");
		r1.addRequirement("Foo");
		try {
			r1.addRequirement("Foo");
			fail("Duplicate requirements not detected.");
		} catch (MilliwaysException e) {
			// OK
		}
	}

	public void testGetRequirements() throws MilliwaysException {
		Race tr1 = milliways.defineRace("tr1");
		tr1.addRequirement("Foo");
		tr1.addRequirement("Bar");
		List<String> req = tr1.getRequirements();
		
		assertTrue("Missing requirement",req.contains("Foo"));
		assertTrue("Missing requirement",req.contains("Bar"));
	}

    public void testGetRequirementsOrder() throws MilliwaysException {
        Race tr1 = milliways.defineRace("tr1");
        Race tr2 = milliways.defineRace("tr2");
        tr1.addRequirement("Foo");
        tr1.addRequirement("Bar");
        tr2.addRequirement("Bar");
        tr2.addRequirement("Foo");
        String[] a1 = tr1.getRequirements().toArray(new String[tr1.getRequirements().size()]);
        String[] a2 = tr2.getRequirements().toArray(new String[tr2.getRequirements().size()]);
        assertTrue("Wrong order of requirements",Arrays.equals(a1, a2));
    }
}
