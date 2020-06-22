package delivery;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private int ID;
	private String address;
	private String phone;
	private String email;
	private List<Order> orders=new ArrayList<>();
	
	public Customer(int id,String name,String address,String phone,String email) {
		this.ID=id;
		this.name=name;
		this.address=address;
		this.phone=phone;
		this.email=email;
		
	}
	
	public int getId() {
		return ID;
	}
	
	public void addOrder(Order o) {
		orders.add(o);
	}
	
	public List<Order> getOrders(){
		return orders;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name+", "+address+", "+phone+", "+email;
	}
	
}
