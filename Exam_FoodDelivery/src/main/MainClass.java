package main;

import delivery.Delivery;
import delivery.DeliveryException;

public class MainClass {

    public static void main(String[] args) throws DeliveryException {
        Delivery ds = new Delivery();

        int id = ds.newCustomer("Jon Snow", "1 Castle Black", "+0 610 555 555", "jon@night.watch.org");

        ds.newMenuItem("Hamburger", 5.00, "Fastfood", 10);
        ds.newMenuItem("Cheeseburger", 5.50, "Fastfood", 10);
        ds.newMenuItem("Fries", 1.50, "Side", 16);
        
        System.out.println(ds.findItem(""));
        // [[Fastfood] Cheeseburger : 5.50, [Fastfood] Hamburger : 5.00, [Side] Fries : 1.50]

        int ido = ds.newOrder(id);
        
        try{
            ds.addItem(ido, "burger", 1);
        }catch(DeliveryException e){
            System.out.println("Cannot add 'burger' since it is ambiguous.");
        }
        
        ds.addItem(ido, "Hamburger", 1);
        ds.addItem(ido, "Cheese", 2);
        ds.addItem(ido, "Fries", 3);
        
        System.out.println(ds.showOrder(ido));
        // [Hamburger, 1, Cheeseburger, 2, Fries, 3]
        
        ds.confirm(ido);
        
        System.out.println(String.format("%.2f",ds.totalOrder(ido))); // 20.50
        
        ds.start(ido);
        ds.deliver(ido);
        
        System.out.println(ds.getStatus(ido)); // ON_DELIVERY
        
        ds.complete(ido);
        
        System.out.println(String.format("%.2f",ds.totalCustomer(id))); // 20.50
        
        System.out.println(ds.bestCustomers());
        // {20.5=[Jon Snow, 1 Castle Black, +0 610 555 555, jon@night.watch.org]}

    }

}
