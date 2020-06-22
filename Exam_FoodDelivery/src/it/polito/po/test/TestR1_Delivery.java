package it.polito.po.test;

import java.util.Collections;
import java.util.List;

import delivery.Delivery;
import delivery.DeliveryException;
import junit.framework.TestCase;

public class TestR1_Delivery extends TestCase {

    public void testNewCustomer() throws DeliveryException{
        Delivery ds = new Delivery();
        
        String name="John";
        String address="Duca degli Abruzzi 24";
        String phone="011 555 44 3";
        String email="john@polito.it";
        int id = ds.newCustomer(name, address, phone, email);
        
        assertEquals("Wrong initial id for new customer",1,id);
    }

    public void testNewCustomerDuplicate() throws DeliveryException{
        Delivery ds = new Delivery();
        
        String name="John";
        String address="Duca degli Abruzzi 24";
        String phone="011 555 44 3";
        String email="john@polito.it";
        ds.newCustomer(name, address, phone, email);
        
        try{
            ds.newCustomer(name, address, phone, email);
            fail("Duplicate customer not detected");
        }catch(DeliveryException e){
            // OK
        }
    }

    public void testTwoCustomers() throws DeliveryException{
        Delivery ds = new Delivery();
        
        ds.newCustomer("some", "strange", "phone", "email");
        
        
        String name="John";
        String address="Duca degli Abruzzi 24";
        String phone="011 555 44 3";
        String email="john@polito.it";
        int id = ds.newCustomer(name, address, phone, email);
        
        assertEquals("Wrong initial id for new customer",2,id);

    }

    public void testCustomerInfo() throws DeliveryException{
        Delivery ds = new Delivery();
        
        ds.newCustomer("some", "strange", "phone", "email");
        
        
        String name="John";
        String address="Duca degli Abruzzi 24";
        String phone="011 555 44 3";
        String email="john@polito.it";
        int id = ds.newCustomer(name, address, phone, email);
        
        // "NOME, INDIRIZZO, TEL, EMAIL"
        String customer = ds.customerInfo(id);
        
        String expected = name + ", " + address + ", " + phone + ", " + email;
        
        assertNotNull("Missing customer info",customer);
        assertTrue("Wrong customer info, expected ["+expected+"] but was [" + customer + "]",
                expected.replaceAll(" ", "").toLowerCase().equals(customer.replaceAll(" ", "").toLowerCase()));
    }
    
    public void testCustomerList() throws DeliveryException{
        Delivery ds = new Delivery();
        
        String name="John";
        String address="Duca degli Abruzzi 24";
        String phone="011 555 44 3";
        String email="john@polito.it";
        ds.newCustomer(name, address, phone, email);
        ds.newCustomer("Stuart", "1234 Lincoln Drive", "+1 610 555 555", "stuart@minions.org");
        ds.newCustomer("Kevin", "1234 Lincoln Drive", "+1 610 555 556", "kevin@minions.org");
        ds.newCustomer("Bob", "1234 Lincoln Drive", "+1 610 555 557", "bob@minions.org");
        
        List<String> customers = ds.listCustomers();
        
        assertNotNull("No customer list returned",customers);
        assertEquals("Wrong number of customers",4,customers.size());
        assertTrue("Wrong order",customers.get(0).contains("Bob"));
        assertTrue("Wrong order",customers.get(1).contains("John"));
        assertTrue("Wrong order",customers.get(2).contains("Kevin"));
    }
    
    public void testItem(){
        Delivery ds = new Delivery();

        ds.newMenuItem("Hamburger", 5.50, "Fast food", 10);
        
        List<String> item = ds.findItem("Hamburger");
        
        assertNotNull("Could not find any item",item);
        assertEquals("No element",1,item.size());
    }

    public void testItemFmt(){
        Delivery ds = new Delivery();

        ds.newMenuItem("Hamburger", 5.50, "Fastfood", 10);
        
        List<String> item = ds.findItem("Hamburger");
        
        assertNotNull("Could not find any item",item);
        
        String it = item.get(0);
        
        String expected = "[Fastfood] Hamburger : 5.50";
        assertTrue("Wrong customer info, expected ["+expected+"] but was [" + it + "]",
                expected.replaceAll(" ", "").toLowerCase().equals(it.replaceAll(" ", "").replaceAll(",",".").toLowerCase()));
    }

    public void testItemFind(){
        Delivery ds = new Delivery();

        ds.newMenuItem("Hamburger", 5.50, "Fastfood", 10);
        ds.newMenuItem("Pizza margherita", 7.50, "Piatto unico", 15);
        ds.newMenuItem("Pizza capircciosa", 8.50, "Piatto unico", 15);
        ds.newMenuItem("Penne al pomodoro", 8.00, "Primo", 15);
        ds.newMenuItem("Spaghetti cacio e pepe", 9.00, "Primo", 15);
        
        List<String> item = ds.findItem("Spaghetti");
        
        assertNotNull("Could not find any item",item);
        assertEquals("Could not find any item",1,item.size());
        assertTrue("Wrong item found",item.get(0).contains("cacio"));
        
        item = ds.findItem("Pizza");
        
        assertEquals("Could not find any item",2,item.size());
        
    }

    public void testItemFindCaseInsensitive(){
        Delivery ds = new Delivery();

        ds.newMenuItem("Hamburger", 5.50, "Fastfood", 10);
        ds.newMenuItem("Pizza margherita", 7.50, "Piatto unico", 15);
        ds.newMenuItem("Pizza capricciosa", 8.50, "Piatto unico", 15);
        ds.newMenuItem("Penne al pomodoro", 8.00, "Primo", 15);
        ds.newMenuItem("Spaghetti cacio e pepe", 9.00, "Primo", 15);
        
        List<String> items = ds.findItem("pIZZA");
        
        assertNotNull("Could not find any item matching pIZZA",items);
        assertEquals("Expected two items matching pIZZA",2,items.size());
        Collections.sort(items);
        assertTrue("Wrong item found, expecting 'Pizza capricciosa'",items.get(0).contains("capricciosa"));
    }


    public void testItemFindAll(){
        Delivery ds = new Delivery();

        ds.newMenuItem("Pizza margherita", 7.50, "Piatto unico", 15);
        ds.newMenuItem("Pizza capircciosa", 8.50, "Piatto unico", 15);
        ds.newMenuItem("Penne al pomodoro", 8.00, "Primo", 15);
        ds.newMenuItem("Spaghetti cacio e pepe", 9.00, "Primo", 15);
        ds.newMenuItem("Hamburger", 5.50, "Fastfood", 10);
        
        List<String> item = ds.findItem("");
        
        assertEquals("Could not retriev all items",5,item.size());
        assertTrue("Wrong item found",item.get(0).contains("burger"));
        assertTrue("Wrong item found",item.get(4).contains("cacio"));

    }
}
