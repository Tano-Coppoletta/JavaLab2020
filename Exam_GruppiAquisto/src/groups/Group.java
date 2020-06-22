package groups;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Group {
	private String name;
	private Product product;
	private List<Customer> customers=new ArrayList<>();
	private List<Supplier> suppliers=new ArrayList<>();
	private List<Bid> bids=new ArrayList<>();
	private Map<Supplier,Integer> voti=new HashMap<>();
	
	public Group(String name,Product p) {
		this.name=name;
		this.product=p;
	}
	
	public void addCustomer(Customer c) {
		customers.add(c);
	}
	
	public Product getProduct() {
		return product;
	}
	
	public int getNumCustomer() {
		return customers.size();
	}
	
	public String getProductName() {
		return product.getName();
	}
	
	public List<Bid> getBid(){
		return bids;
	}
	
	public String getName() {
		return name;
	}
	
	public void addSupplier(Supplier s) {
		suppliers.add(s);
	}
	
	public void addBid(Bid b) {
		bids.add(b);
	}
	
	public boolean isSupplier(Supplier s) {
		if(s!=null) {
			for(Supplier s1:suppliers) {
				if(s1!=null) {
					if(s.getName().equals(s1.getName()))
						return true;
				}
			}
		}
		return false;
	}
	public String getBidsString() {
		List<String> s= bids.stream().sorted(Comparator.comparingInt(Bid::getPrice).thenComparing(Bid::getSupplierName))
				.map(Bid::toString).collect(Collectors.toList());
		StringBuffer s1=new StringBuffer();
		for(String l:s) {
			s1.append(l).append(",");
		}
		s1.deleteCharAt(s1.length()-1);
		return s1.toString();
		
	}
	
	public void addVoto(Supplier s) {
		if(voti.containsKey(s)) {
			int i=voti.remove(s);
			i++;
			voti.put(s, i);
		}
		voti.put(s, 1);
	}
	
	public Bid getBid(Supplier s) {
		for(Bid b:bids) {
			if(s.getName().equals(b.getSupplierName()))
				return b;
		}
		return null;
	}
	public String votiToString() {
		List<String> l=voti.keySet().stream()
				.sorted(Comparator.comparing(Supplier::getName)).map(s->s.getName()+":"+voti.get(s)).collect(Collectors.toList());
		StringBuffer s=new StringBuffer();
		for(String s1:l) {
			s.append(s1).append(",");
		}
		s.deleteCharAt(s.length()-1);
		return s.toString();
	}
	
	public String getWinning() {
		int max=0;
		Supplier m=null;
		for(Supplier s:voti.keySet()) {
			if(voti.get(s)>max) {
				max=voti.get(s);
				m=s;
			}else if(voti.get(s)==max) {
				if(this.getBid(s).getPrice()<this.getBid(m).getPrice()) {
					max=voti.get(s);
					m=s;
				}
			}
		}
		return m.getName()+":"+max;
	}
	
	public int getMaxPrice() {
		int max=0;
		for(Bid b:bids)
			if(b.getPrice()>max)
				max=b.getPrice();
		return max;
	}
}
