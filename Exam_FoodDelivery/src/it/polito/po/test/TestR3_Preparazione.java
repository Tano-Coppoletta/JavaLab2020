package it.polito.po.test;

import delivery.Delivery;
import delivery.DeliveryException;
import junit.framework.TestCase;

public class TestR3_Preparazione extends TestCase {
    
    private Delivery ds;
    private int id2;
    private int ido1;
    
    public void setUp() throws DeliveryException{
        ds = new Delivery();
        String name="John";
        String address="Duca degli Abruzzi 24";
        String phone="011 555 44 3";
        String email="john@polito.it";
        ds.newCustomer(name, address, phone, email);
        id2 = ds.newCustomer("Stuart", "1234 Lincoln Drive", "+1 610 555 555", "stuart@minions.org");
        ds.newCustomer("Kevin", "1234 Lincoln Drive", "+1 610 555 556", "kevin@minions.org");
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

    }

    public void testNewOrder() throws DeliveryException{
        Delivery.OrderStatus s = ds.getStatus(ido1);
        
        assertEquals("Wrong status for new order",Delivery.OrderStatus.NEW,s);
    }

    public void testBadOrder() {
        try {
            ds.getStatus(99);
            fail("Wrong order id not detected, expected exception");
        } catch (DeliveryException e) {
            // OK
        }        
    }
    public void testConfirm() throws DeliveryException{
        
        int estimate = ds.confirm(ido1);
        
        
        Delivery.OrderStatus s = ds.getStatus(ido1);
        
        assertEquals("Wrong status for new order",Delivery.OrderStatus.CONFIRMED,s);
        assertEquals("Wrong estimate value",36,estimate);
    }
    public void testStart() throws DeliveryException{
        
        ds.confirm(ido1);
        int estimate = ds.start(ido1);
        
        Delivery.OrderStatus s = ds.getStatus(ido1);
        
        assertEquals("Wrong status for new order",Delivery.OrderStatus.PREPARATION,s);
        assertEquals("Wrong estimate value",31,estimate);
    }
    public void testBadStart() {
        
        //ds.confirm(ido1);
        try {
            ds.start(ido1);
            fail("Wrong order status not detected, expected exception");
        } catch (DeliveryException e) {
            // OK
        }
    }
    public void testDeliver() throws DeliveryException{
        
        ds.confirm(ido1);
        ds.start(ido1);
        int estimate = ds.deliver(ido1);
        
        Delivery.OrderStatus s = ds.getStatus(ido1);
        
        assertEquals("Wrong status for new order",Delivery.OrderStatus.ON_DELIVERY,s);
        assertEquals("Wrong estimate value",15,estimate);
    }
    public void testBadDeliver() throws DeliveryException{
        
        ds.confirm(ido1);
        //ds.start(ido1);
        
        try {
            ds.deliver(ido1);
            fail("Wrong order status not detected, expected exception");
        } catch (DeliveryException e) {
            // OK
        }
    }
    public void testComplete() throws DeliveryException{
        
        ds.confirm(ido1);
        ds.start(ido1);
        ds.deliver(ido1);
        ds.complete(ido1);
        
        Delivery.OrderStatus s = ds.getStatus(ido1);
        
        assertEquals("Wrong status for new order",Delivery.OrderStatus.DELIVERED,s);
    }
    public void testBadComplete() throws DeliveryException{
        
        ds.confirm(ido1);
        ds.start(ido1);
        //ds.deliver(ido1);
        
        try {
            ds.complete(ido1);
            fail("Wrong order status not detected, expected exception");
        } catch (DeliveryException e) {
            // OK
        }
    }
}
