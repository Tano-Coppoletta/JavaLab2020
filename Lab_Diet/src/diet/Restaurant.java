package diet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;
import java.util.LinkedList;

import diet.Order.OrderStatus;

/**
 * Represents a restaurant in the take-away system
 *
 */
public class Restaurant {
	private String name;
	private Food food;
	private Map<String,Menu> menu=new TreeMap<>();
	private List<Intervallo> intervalli=new LinkedList<>();
	private Set<Order> ordini=new TreeSet<>((a,b)-> {
		int cmp;
		cmp=a.getUser().getLastName().compareTo(b.getUser().getLastName());
		if(cmp!=0)
			return cmp;
		cmp=a.getUser().getFirstName().compareTo(b.getUser().getFirstName());
		if(cmp!=0)
			return cmp;
		cmp=a.getTime().compareTo(b.getTime());
		if(cmp!=0)
			return cmp;
		return 0;
		}
	);
	
	/**
	 * Constructor for a new restaurant.
	 * 
	 * Materials and recipes are taken from
	 * the food object provided as argument.
	 * 
	 * @param name	unique name for the restaurant
	 * @param food	reference food object
	 */
	public Restaurant(String name, Food food) {
		this.name=name;
		this.food=food;
	}
	
	/**
	 * gets the name of the restaurant
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define opening hours.
	 * 
	 * The opening hours are considered in pairs.
	 * Each pair has the initial time and the final time
	 * of opening intervals.
	 * 
	 * for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00, 
	 * is thoud be called as {@code setHours("08:15", "14:00", "19:00", "00:00")}.
	 * 
	 * @param hm a list of opening hours
	 */
	public void setHours(String ... hm) {
		for(int i=0;i<hm.length-1;i++) {
			if(i%2==0) {
				intervalli.add(new Intervallo(hm[i],hm[i+1]));
			}
		}
	}
	
	public Menu getMenu(String name) {
		return menu.get(name);
	}
	
	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * 
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu m=new Menu(name,food);
		menu.put(name, m);
		return m;
	}
	/**
	 * @return true se è aperto in quell'orario
	 * */
	public boolean aperto(String h) {
		for(Intervallo i:intervalli) {
			if(i.contenuto(h)) {
				return true;
			}
		}
		return false;
	}
	public String successivo(String h) {
		for(Intervallo i:intervalli) {
			if(i.successivo(h)) {
				return i.getIN();
			}
		}//se è dopo l'ultima chiusura torno il primo del giorno dopo
		 return ((LinkedList<Intervallo>) intervalli).getLast().getIN();
		
	}
	
	public void nuovoOrdine(Order o) {
		ordini.add(o);
	}

	/**
	 * Find all orders for this restaurant with 
	 * the given status.
	 * 
	 * The output is a string formatted as:
	 * <pre>
	 * Napoli, Judi Dench : (19:00):
	 * 	M6->1
	 * Napoli, Ralph Fiennes : (19:00):
	 * 	M1->2
	 * 	M6->1
	 * </pre>
	 * 
	 * The orders are sorted by name of restaurant, name of the user, and delivery time.
	 * 
	 * @param status the status of the searched orders
	 * 
	 * @return the description of orders satisfying the criterion
	 */
	public String ordersWithStatus(OrderStatus status) {
		StringBuffer s=new StringBuffer();
		for(Order o:ordini) {
			if(o.getStatus()==status) {
				s.append(o.toString()).append("\n");
			}
		}
		return s.toString();
	}
}
