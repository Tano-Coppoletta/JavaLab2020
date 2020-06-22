package delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import delivery.Delivery.OrderStatus;

public class Order {
	private int ID;
	private Map<Item,Integer> items=new HashMap<>();
	private Customer customer;
	private OrderStatus status;
	
	public Order(int id,Customer c) {
		ID=id;
		this.customer=c;
		status=OrderStatus.NEW;
	}
	
	public int addItem(Item i,int q) {
		int quantita=q;
		if(items.containsKey(i)) {
			quantita+=items.remove(i);			
		}
		
		items.put(i, quantita);
		return quantita;
	}
	
	public List<String> stampaItem() {
//		List<String> l=new ArrayList<>();
//		for(Item i:items.keySet()) {
//			l.add(i.getDescription()+", "+items.get(i));
//		}
//		return l;
		return items.entrySet().stream()
				.map(s->s.getKey().getDescription()+", "+s.getValue().intValue())
				.collect(Collectors.toList());
	}
	
	public double price() {
		double prezzo=0;
		for(Item i:items.keySet()) {
			prezzo=prezzo+i.getPrice()*items.get(i);
		}
		return prezzo;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void changeStatus(OrderStatus s) {
		status=s;
	}
	
	public int getMaxTime() {
		int max=0;
		for(Item i:items.keySet()) {
			if(i.getTime()>max)
				max=i.getTime();
		}
		return max;
	}
	
	public boolean isConfimed() {
		if(status!=OrderStatus.NEW)
			return true;
		return false;
	}
	
	public boolean isCustomer(Customer c) {
		if(c.getId()==this.customer.getId())
			return true;
		return false;
	}
	public String getCustomer() {
		return customer.toString();
	}
}
