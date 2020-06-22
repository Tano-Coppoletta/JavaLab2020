package groups;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Supplier {
	private String name;
	private Map<String,Product> products=new TreeMap<>();
	private Map<String,Group> offerte=new HashMap<>();
	private int NBids=0;
	
	public Supplier(String n) {
		this.name=n;
	}
	public String getName() {
		return name;
	}
	
	public void addProduct(String s,Product p) {
		products.put(s, p);
	}
	public List<String> getProducts(){
		return products.keySet().stream().collect(Collectors.toList());
	}
	
	public boolean isPresenteProduct(Product p) {
		return products.containsKey(p.getName());
	}
	
	public void addOfferta(String name,Group g) {
		offerte.put(name, g);
		NBids++;
	}
	public boolean isPresenteGroup(String name) {
		return offerte.containsKey(name);
	}
	
	public int getNBids() {
		return NBids;
	}
	
	
	
}
