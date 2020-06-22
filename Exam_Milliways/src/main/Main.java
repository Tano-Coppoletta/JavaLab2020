package main;

import it.polito.oop.milliways.Hall;
import it.polito.oop.milliways.MilliwaysException;
import it.polito.oop.milliways.Party;
import it.polito.oop.milliways.Race;
import it.polito.oop.milliways.Restaurant;

public class Main {

	public static void main(String[] args) throws MilliwaysException {
	    Restaurant milliways = new Restaurant();

		// Create a fews standard races
		Race race1 = milliways.defineRace("Amoeboid Zingatularians");
		Race race2 = milliways.defineRace("Betelgeusians");
		Race race3 = milliways.defineRace("Blagulon Kappans");
		Race race4 = milliways.defineRace("Golgafrinchans");
		Race race5 = milliways.defineRace("Jatravartids");
		Race race6 = milliways.defineRace("Krikkiters");
		Race race7 = milliways.defineRace("Silastic Armourfiends of Striterax");

		// Add some random requirements
		for (Race r : new Race[] { race1, race2, race3, race4, race5 })
			r.addRequirement("Class M atmosphere (nitrogen-oxygen)");
		for (Race r : new Race[] { race6, race7 })
			r.addRequirement("Class X atmosphere (methane)");
		for (Race r : new Race[] { race1, race3, race5 })
			r.addRequirement("Low gravity (less than 2g)");

		race3.addRequirement("Soothing music");
		race5.addRequirement("Vegeterian meals");
		race7.addRequirement("Live food");
		
		//race3.getRequirements();

		// Create party
		Party party1 = milliways.createParty();
		party1.addCompanions(race1, 1);
		party1.addCompanions(race2, 2);
		party1.addCompanions(race1, 2);
		System.out.println("Party of " + party1.getNum()); // Party of 5
		System.out.println(party1.getRequirements()); // [Class M atmosphere (nitrogen-oxygen), Low gravity (less than 2g)]
		System.out.println("");

		// Prepare halls
		Hall main_hall = milliways.defineHall(42);
		for (String facility : new String[] { "Class M atmosphere (nitrogen-oxygen)", "Soothing music",
				"Low gravity (less than 2g)" })
			main_hall.addFacility(facility);
		System.out.println("Hall id: " + main_hall.getId()); // Hall id: 42
		System.out.println("facilities: " + main_hall.getFacilities()); 
		// facilities: [Class M atmosphere (nitrogen-oxygen), Low gravity (less than 2g), Soothing music]

		Hall h1 = milliways.seat(party1);
		System.out.println("Party composition: " + party1.getDescription()); // Party composition: {Betelgeusians=2, Amoeboid Zingatularians=3}
		System.out.println("      seated in hall:" + h1.getId()); // seated in hall:42

		System.out.println("");
		
		System.out.println(milliways.statComposition()); // {Amoeboid Zingatularians=3, Betelgeusians=2}

		Party party2 = milliways.createParty();
		party2.addCompanions(race1, 1);
		party2.addCompanions(race2, 100);
		party2.addCompanions(race3, 2);
		
		Hall h2 = milliways.seat(party2);
        System.out.println("Party composition: " + party2.getDescription()); 
        // Party composition: {Blagulon Kappans=2, Betelgeusians=100, Amoeboid Zingatularians=1}
        System.out.println("      seated in hall:" + h2.getId()); // seated in hall:42
		System.out.println("");
		
		System.out.println(milliways.statComposition());
		// {Amoeboid Zingatularians=4, Betelgeusians=102, Blagulon Kappans=2}
		
		Hall hall2 = milliways.defineHall(2);
		main_hall.addFacility("Live food");
		hall2.addFacility("Live food");
		hall2.addFacility("Class X atmosphere (methane)");
		
		System.out.println("Facilities:\n"+milliways.statFacility());
		// [Live food, Class M atmosphere (nitrogen-oxygen), Class X atmosphere (methane), Low gravity (less than 2g), Soothing music]
		
	      Hall hall3 = milliways.defineHall(3);
	      hall3.addFacility("Heavy gravity (>2g)");
	      hall3.addFacility("Class M atmosphere (oxygen)");

		System.out.println("Halls stat:\n"+milliways.statHalls()); // {2=[2, 3], 4=[42]}
	}

}
