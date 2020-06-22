package groups;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

import java.util.*;


public class GroupHandling {
	private Map<String,Product> products=new HashMap<>();
	private Map<String,Supplier> suppliers=new HashMap<>();
	private Map<String,Group> groups=new HashMap<>();
	private Map<String,Customer> customers=new HashMap<>();
	
//R1	
	public void addProduct (String productTypeName, String... supplierNames) 
			throws GroupHandlingException {
		if(products.containsKey(productTypeName))
			throw new GroupHandlingException("prodotto già definito");
		Product p=new Product(productTypeName);
		for(String s:supplierNames) {
			if(suppliers.containsKey(s)) {
				p.addSupplier(s, suppliers.get(s));
				suppliers.get(s).addProduct(productTypeName, p);
			}else {
				Supplier r=new Supplier(s);
				r.addProduct(productTypeName, p);
				suppliers.put(s, r);
				
				p.addSupplier(s, r);
			}
		}
		products.put(productTypeName, p);
	}
	
	public List<String> getProductTypes (String supplierName) {
		if(suppliers.containsKey(supplierName))
			return suppliers.get(supplierName).getProducts();
		return null;
	}
	
//R2	
	public void addGroup (String name, String productName, String... customerNames) 
			throws GroupHandlingException {
		if(groups.containsKey(name))
			throw new GroupHandlingException("gruppo già definito");
		if(!products.containsKey(productName))
			throw new GroupHandlingException("prodotto non definito");
		Group g=new Group(name,products.get(productName));
		for(String s:customerNames) {
			if(customers.containsKey(s)) {
				g.addCustomer(customers.get(s));
				customers.get(s).addGroup(g);
			}else {
				Customer c=new Customer(s);
				c.addGroup(g);
				g.addCustomer(c);
				customers.put(s, c);
			}
		}
		groups.put(name, g);
	}
	
	public List<String> getGroups (String customerName) {
		if(customers.containsKey(customerName))
			return customers.get(customerName).getGroups();
		return null;
	}

//R3
	public void setSuppliers (String groupName, String... supplierNames)
			throws GroupHandlingException {
		if(!groups.containsKey(groupName))
			throw new GroupHandlingException("gruppo indefinito");
		Group g=groups.get(groupName);
		for(String s:supplierNames) {
			if(!suppliers.containsKey(s))
				throw new GroupHandlingException("supplier non definito");
			if(!suppliers.get(s).isPresenteProduct(g.getProduct()))
				throw new GroupHandlingException(s+" non tratta prodotto");
			g.addSupplier(suppliers.get(s));
		}
	}
	
	public void addBid (String groupName, String supplierName, int price)
			throws GroupHandlingException {
		if(!groups.containsKey(groupName))
			throw new GroupHandlingException(groupName+" non presente");
		if(!suppliers.containsKey(supplierName))
			throw new GroupHandlingException(supplierName+ " non presente");
		Group g=groups.get(groupName);
		Supplier s=suppliers.get(supplierName);
		if(!g.isSupplier(s)) 
			throw new GroupHandlingException(groupName+" non fornito da "+supplierName);
		Bid b=new Bid(g,s,price);
		g.addBid(b);
		s.addOfferta(g.getName(), g);
	}
	
	public String getBids (String groupName) {
        return groups.get(groupName).getBidsString();
	}
	
	
//R4	
	public void vote (String groupName, String customerName, String supplierName)
			throws GroupHandlingException {
		Group g=groups.get(groupName);
		Customer c=customers.get(customerName);
		Supplier s=suppliers.get(supplierName);
		if(!c.Gruppook(g)) {
			throw new GroupHandlingException();
		}
		if(!s.isPresenteGroup(g.getName()))
			throw new GroupHandlingException(groupName+" non presnete");
		Bid b=g.getBid(s);
		c.addBid(b);
		g.addVoto(s);
	}
	
	public String  getVotes (String groupName) {
        return groups.get(groupName).votiToString();
	}
	
	public String getWinningBid (String groupName) {
        return groups.get(groupName).getWinning();
	}
	
//R5
	public SortedMap<String, Integer> maxPricePerProductType() { //serve toMap
      return  (SortedMap<String, Integer>) groups.values().stream()
    		  .flatMap(g->g.getBid().stream())
    		//  .sorted((a,b)->{return a.getGroup().getProduct().getName().compareTo(a.getGroup().getProduct().getName());})
    		
    		  .collect(Collectors.toMap(Bid::getProductName, Bid::Max,(p1,p2) -> {return p1 >= p2 ? p1:p2;}, TreeMap::new));
		
	}
	
	public SortedMap<Integer, List<String>> suppliersPerNumberOfBids() {
		return suppliers.values().stream()
				.filter(s->s.getNBids()>0)
				.collect(Collectors.groupingBy(Supplier::getNBids,()->new TreeMap<Integer,List<String>>(reverseOrder()),
				Collectors.mapping(Supplier::getName,Collectors.toList())));
     
	}
	
	public SortedMap<String, Long> numberOfCustomersPerProductType() {
		return customers.values().stream()
				.flatMap(s->s.getGroup().stream())
				.filter(s->s.getNumCustomer()>0)
				.collect(Collectors.groupingBy(Group::getProductName,TreeMap::new,
						Collectors.counting()));
       
	}
	
}
