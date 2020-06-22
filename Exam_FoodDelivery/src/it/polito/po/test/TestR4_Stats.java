package it.polito.po.test;

import java.util.List;
import java.util.Map;

import delivery.Delivery;
import delivery.DeliveryException;
import junit.framework.TestCase;

public class TestR4_Stats extends TestCase {
    private Delivery ds;
    private int id1,id2,id3;
    private int ido1,ido2,ido3,ido4,ido5;
    
    public void setUp() throws DeliveryException{
        ds = new Delivery();
        String name="John";
        String address="Duca degli Abruzzi 24";
        String phone="011 555 44 3";
        String email="john@polito.it";
        id1 = ds.newCustomer(name, address, phone, email);
        id2 = ds.newCustomer("Stuart", "1234 Lincoln Drive", "+1 610 555 555", "stuart@minions.org");
        id3 = ds.newCustomer("Kevin", "1234 Lincoln Drive", "+1 610 555 556", "kevin@minions.org");
        ds.newCustomer("Bob", "1234 Lincoln Drive", "+1 610 555 557", "bob@minions.org");

        ds.newMenuItem("Hamburger", 5.50, "Fastfood", 10);
        ds.newMenuItem("Pizza margherita", 7.50, "Piatto unico", 16);
        ds.newMenuItem("Pizza capricciosa", 8.50, "Piatto unico", 15);
        ds.newMenuItem("Penne al pomodoro", 8.00, "Primo", 15);
        ds.newMenuItem("Spaghetti cacio e pepe", 9.00, "Primo", 15);

        ido1 = ds.newOrder(id2);
        
        ds.addItem(ido1, "burger", 1);
        ds.addItem(ido1, "margherita", 3);
        ds.addItem(ido1, "pomodoro", 2);
        
        ds.confirm(ido1);
        ds.start(ido1);
        ds.deliver(ido1);
        ds.complete(ido1);
        
        ido2 = ds.newOrder(id1);
        ds.addItem(ido2, "pomodoro", 4);
        ds.confirm(ido2);

        ido3 = ds.newOrder(id1);
        ds.addItem(ido3, "cacio e pepe", 10);
        // not confirmed
        
        ido4 = ds.newOrder(id3);        
        ds.addItem(ido4, "burger", 3);
        ds.confirm(ido4);

        ido5 = ds.newOrder(id2);        
        ds.addItem(ido5, "capricciosa", 5);
        ds.confirm(ido5);

    }
    
    public void testTotalCustomer(){
        
        double total = ds.totalCustomer(id2);
        
        assertEquals("Wrong total for customer 2",86.50,total);
        
    }
    
    public void testBestCustomers(){
        Map<Double,List<String>> bc = ds.bestCustomers();
        
        assertNotNull("Missing best customers infor",bc);
        
        // System.out.println(bc);
        // {86.5=[Stuart, 1234 Lincoln Drive, +1 610 555 555, stuart@minions.org], 
        //  32.0=[John, Duca degli Abruzzi 24, 011 555 44 3, john@polito.it], 
        //  16.5=[Kevin, 1234 Lincoln Drive, +1 610 555 556, kevin@minions.org]}
        
        List<String> top = bc.get(86.5);
        
        assertNotNull("Top customer should have 86.5 total",top);
        assertEquals("Top customer should only one",1,top.size());
        assertTrue("Top customer should be Stuart",top.get(0).contains("Stuart"));
        
        List<String> bottom = bc.get(16.5);
        
        assertNotNull("Top customer should have 16.5 total",bottom);
        assertEquals("Top customer should only one",1,bottom.size());
        assertTrue("Top customer should be Stuart",bottom.get(0).contains("Kevin"));
    }

/*
    public void testBestItems(){
        List<String> bi = ds.bestItems();
        
        // System.out.println(bi);
        // [Penne al pomodoro, 48.00, Pizza capricciosa, 42.50, Pizza margherita, 22.50, Hamburger, 22.00]
        
        assertNotNull(bi);
        assertEquals("Expected 4 items",4,bi.size());
        assertTrue("Wrong top item, expected Penne al pomodoro but was " + bi.get(0),
                        bi.get(0).contains("pomodoro"));
        assertTrue("Wrong 3rd item, expected Pizza margherita but was " + bi.get(2),
                bi.get(2).contains("margherita"));
    }
    
    public void testPopularItems(){
        List<String> pi = ds.popularItems();
        
        //System.out.println(pi);
        // [Penne al pomodoro, 6, Pizza capricciosa, 5, Hamburger, 4, Pizza margherita, 3]
        assertNotNull(pi);
        assertEquals("Expected 4 items",4,pi.size());
        assertTrue("Wrong top item, expected Penne al pomodoro but was " + pi.get(0),
                        pi.get(3).contains("margherita"));
    }
    
*/

}
