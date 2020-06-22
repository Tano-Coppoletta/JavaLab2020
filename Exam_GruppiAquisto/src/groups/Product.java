package groups;

import java.util.HashMap;
import java.util.Map;

public class Product {
	private String name;
	private Map<String,Supplier> suppliers=new HashMap<>();
	
	public Product(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addSupplier(String name,Supplier s) {
		suppliers.put(name, s);
	}
}
