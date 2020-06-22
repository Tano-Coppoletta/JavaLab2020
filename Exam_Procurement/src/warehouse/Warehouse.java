package warehouse;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Warehouse {
	private Map<String,Product> products=new HashMap<>();
	private Map<String,Supplier> suppliers=new HashMap<>();
	private String codOrd;
	private int numOrd;
	private Map<String,Order> orders=new HashMap<>();
	
	public Warehouse() {
		codOrd="ORD";
		numOrd=0;
	}
	
	public Product newProduct(String code, String description){
		Product p=new Product(code,description);
		products.put(code, p);
		return p;
	}
	
	public Collection<Product> products(){
		return products.values();
	}

	public Product findProduct(String code){
		return products.get(code);
	}

	public Supplier newSupplier(String code, String name){
		Supplier s=new Supplier(code,name);
		suppliers.put(code, s);
		return s;
	}
	
	public Supplier findSupplier(String code){
		return suppliers.get(code);
	}

	public Order issueOrder(Product prod, int quantity, Supplier supp)
		throws InvalidSupplier {
		if(!supp.isFornito(prod))
			throw new InvalidSupplier();
		numOrd++;
		Order o=new Order(codOrd+numOrd,prod,supp,quantity);
		orders.put(codOrd+numOrd,o);
		prod.addOrder(o);
		supp.addOrder(o);
		return o;
	}

	public Order findOrder(String code) {
		return orders.get(code);
	}
	
	public List<Order> pendingOrders(){
		return orders.values().stream()
				.filter(o->!o.delivered())
				.sorted(Comparator.comparing(Order::getProductCode))
				.collect(Collectors.toList());
	}

	public Map<String,List<Order>> ordersByProduct(){
	    return orders.values().stream()
	    		.collect(Collectors.groupingBy(Order::getProductCode,Collectors.toList()));
	}
	
	public Map<String,Long> orderNBySupplier(){
		return suppliers.values().stream()
				.flatMap(s->s.getOrders().stream())
				.filter(Order::delivered)
				.collect(Collectors.groupingBy(Order::getSupplierName,TreeMap::new,Collectors.counting()));
	}
	
	public List<String> countDeliveredByProduct(){
	    return orders.values().stream()
	    		.filter(Order::delivered)
	    		.collect(Collectors.groupingBy(Order::getProductCode,Collectors.counting()))
	    		.entrySet().stream()
	    		.sorted(Comparator.comparingLong(s->((Entry<String, Long>) s).getValue()).reversed())
	    		.map(s->s.getKey()+" - "+s.getValue())
	    		.collect(Collectors.toList());
	}
}
