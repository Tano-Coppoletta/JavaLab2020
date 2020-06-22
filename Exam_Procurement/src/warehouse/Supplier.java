package warehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Supplier {
	private String code;
	private String nome;
	private List<Product> products=new ArrayList<>();
	private List<Order> orders=new ArrayList<>();
	
	public Supplier(String code,String nome) {
		this.code=code;
		this.nome=nome;
	}

	public String getCodice(){
		return code;
	}

	public String getNome(){
		return nome;
	}
	
	public void newSupply(Product product){
		products.add(product);
		product.addSupplier(this);
	}
	
	public List<Order> getOrders(){
		return orders;
	}
	
	public List<Product> supplies(){
		return products.stream().sorted(Comparator.comparing(Product::getDescription)).collect(Collectors.toList());
	}
	
	public boolean isFornito(Product p) {
		for(Product p1:products) {
			if(p.getCode().equals(p1.getCode()))
				return true;
		}
		return false;
	}
	
	public void addOrder(Order o) {
		orders.add(o);
	}
}
