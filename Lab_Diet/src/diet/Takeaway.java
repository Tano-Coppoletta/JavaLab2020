package diet;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {
	private Map<String,Restaurant> rest=new TreeMap<>();
	private Map<String,User> user=new TreeMap<>();
	/**
	 * Adds a new restaurant to the take-away system
	 * 
	 * @param r the restaurant to be added
	 */
	public void addRestaurant(Restaurant r) {
		rest.put(r.getName(),r);
	}
	
	/**
	 * Returns the collections of restaurants
	 * 
	 * @return collection of added restaurants
	 */
	public Collection<String> restaurants() {
		return rest.keySet();
	}
	
	/**
	 * Define a new user
	 * 
	 * @param firstName first name of the user
	 * @param lastName  last name of the user
	 * @param email     email
	 * @param phoneNumber telephone number
	 * @return
	 */
	public User registerUser(String firstName, String lastName, String email, String phoneNumber) {
		User u=new User(firstName,lastName,email,phoneNumber);
		user.put(lastName+firstName, u);
		return u;
	}
	
	/**
	 * Gets the collection of registered users
	 * 
	 * @return the collection of users
	 */
	public Collection<User> users(){
		return user.values();
	}
	
	/**
	 * Create a new order by a user to a given restaurant.
	 * 
	 * The order is initially empty and is characterized
	 * by a desired delivery time. 
	 * 
	 * @param user				user object
	 * @param restaurantName	restaurant name
	 * @param h					delivery time hour
	 * @param m					delivery time minutes
	 * @return
	 */
	public Order createOrder(User user, String restaurantName, int h, int m) {
		Restaurant r=rest.get(restaurantName);
		String ora=h+":"+m;
		Order o;
		if(r.aperto(ora)) {
			o=new Order(user,restaurantName,ora);
			r.nuovoOrdine(o);
		}else {
			o=new Order(user,restaurantName,r.successivo(ora));
			r.nuovoOrdine(o);
		}
		return o;
	}
	
	/**
	 * Retrieves the collection of restaurant that are open
	 * at the given time.
	 * 
	 * @param time time to check open
	 * 
	 * @return collection of restaurants
	 */
	public Collection<Restaurant> openedRestaurants(String time){
		List<Restaurant> list=new LinkedList<>();
		for(String s:rest.keySet()) {
			if(rest.get(s).aperto(time)) {
				((LinkedList<Restaurant>) list).addLast(rest.get(s));
			}
		}
		return list;
	}

	
	
}
