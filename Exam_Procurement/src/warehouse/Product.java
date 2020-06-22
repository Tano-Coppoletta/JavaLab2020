package warehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Product {
	private String code;
	private String description;
	private int quantity;
	private List<Supplier> suppliers=new ArrayList<>();
	private List<Order> orders=new ArrayList<>();

	
	public Product(String code,String description) {
		this.code=code;
		this.description=description;
		quantity=0;
	}
	public String getCode(){
		
		return code;
	}

	public String getDescription(){
		return description;
	}
	
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}

	public void decreaseQuantity(){
		if(quantity!=0)
			quantity--;
	}

	public int getQuantity(){
		return quantity;
	}
	
	public void addSupplier(Supplier s) {
		suppliers.add(s);
	}

	public List<Supplier> suppliers(){
		return suppliers.stream().sorted(Comparator.comparing(Supplier::getNome)).collect(Collectors.toList());
	}
	
	public void addOrder(Order o) {
		orders.add(o);
	}

	public List<Order> pendingOrders(){
		return orders.stream().filter(o->!o.delivered())
				.sorted(Comparator.comparingInt(Order::getQuantity).reversed())
				.collect(Collectors.toList());
	}
}
