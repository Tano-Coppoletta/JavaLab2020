package it.polito.po.test;

import java.util.Collections;
import java.util.List;

import delivery.Delivery;
import delivery.DeliveryException;
import junit.framework.TestCase;

public class TestR2_Orders extends TestCase {
    private Delivery ds;
    private int id1,id2;
    
    public void setUp() throws DeliveryException{
        ds = new Delivery();
        String name="John";
        String address="Duca degli Abruzzi 24";
        String phone="011 555 44 3";
        String email="john@polito.it";
        id1 = ds.newCustomer(name, address, phone, email);
        id2 = ds.newCustomer("Stuart", "1234 Lincoln Drive", "+1 610 555 555", "stuart@minions.org");
        ds.newCustomer("Kevin", "1234 Lincoln Drive", "+1 610 555 556", "kevin@minions.org");
        ds.newCustomer("Bob", "1234 Lincoln Drive", "+1 610 555 557", "bob@minions.org");

        ds.newMenuItem("Hamburger", 5.50, "Fastfood", 10);
        ds.newMenuItem("Pizza margherita", 7.50, "Piatto unico", 15);
        ds.newMenuItem("Pizza capircciosa", 8.50, "Piatto unico", 15);
        ds.newMenuItem("Penne al pomodoro", 8.00, "Primo", 15);
        ds.newMenuItem("Spaghetti cacio e pepe", 9.00, "Primo", 15);

    }
    
    public void testNewOrder(){
        
        int ido1 = ds.newOrder(id2);
        int ido2 = ds.newOrder(id1);
        
        assertEquals("Wrong id for first order",1,ido1);
        assertEquals("Wrong id for second order",2,ido2);
    }

    public void testAddItems() throws DeliveryException{
        
        int ido1 = ds.newOrder(id2);
        
        int qty = ds.addItem(ido1, "margherita", 2);
        
        assertEquals("Wrong quantity returned by add item",2,qty);
        
    }

    public void testAddItemsTwice() throws DeliveryException{
        
        int ido1 = ds.newOrder(id2);
        
        ds.addItem(ido1, "margherita", 3);
        int qty = ds.addItem(ido1, "margherita", 2);
        
        assertEquals("Wrong quantity returned by add item",5,qty);
        
    }
    
    public void testAddItemsBadOrder() throws DeliveryException{
        
        ds.newOrder(id2);
        
        try{
            ds.addItem(99, "margherita", 3);
            fail("Invalid order id not detected, expected exception");
        }catch(DeliveryException e){
            // OK
        }
    }

    public void testAddItemsBadString() throws DeliveryException{
        
        int ido1 = ds.newOrder(id2);
        
        try{
            ds.addItem(ido1, "parmigiana", 3);
            fail("Invalid string not detected, expected exception");
        }catch(DeliveryException e){
            // OK
        }
    }

    public void testAddItemsAmbiguous() throws DeliveryException{
        
        int ido1 = ds.newOrder(id2);
        
        try{
            ds.addItem(ido1, "Pizza", 1);
            fail("Ambiguous string not detected, expected exception");
        }catch(DeliveryException e){
            // OK
        }
    }
    
    public void testShowOrder() throws DeliveryException{
        int ido1 = ds.newOrder(id2);
        
        ds.addItem(ido1, "burger", 1);
        ds.addItem(ido1, "margherita", 3);
        ds.addItem(ido1, "pomodoro", 2);
        
        List<String> order = ds.showOrder(ido1);

        assertNotNull("Missing order details",order);
        assertEquals("Wrong number of order items",3,order.size());
        
        Collections.sort(order);
        
        assertTrue("Expecting an Hamburger",order.get(0).contains("Hamburger"));
        assertTrue("Expecting an Penne",order.get(1).contains("Penne"));
        assertTrue("Expecting an Pizza",order.get(2).contains("Pizza"));
        
    }

    public void testShowOrderBad() throws DeliveryException{
        ds.newOrder(id2);
        
        try{
            ds.showOrder(99);
            fail("Wrong order id not detected, expected exception");
        }catch(DeliveryException e){
            // OK
        }

    }
    
    public void testTotalOrder() throws DeliveryException{
        int ido1 = ds.newOrder(id2);
        
        ds.addItem(ido1, "burger", 1);
        ds.addItem(ido1, "margherita", 3);
        ds.addItem(ido1, "pomodoro", 2);
        
        ds.showOrder(ido1);

        double total = ds.totalOrder(ido1);
        
        assertEquals("Wrong order total",44.0,total);
        
    }
}
