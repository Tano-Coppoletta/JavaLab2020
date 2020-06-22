package diet;

import java.util.Map;
import java.util.TreeMap;

/**
 * Represents an order in the take-away system
 */
public class Order {
	private User u;
	private String restName;
	private Tempo tempo;
	private Map<String,Integer> menu=new TreeMap<>();
	
	public Order(User u,String restName,String ora) {
		this.u=u;
		this.restName=restName;
		this.tempo=new Tempo(ora);
	}
	/**
	 * Defines the possible order status
	 */
	public enum OrderStatus {
		ORDERED, READY, DELIVERED;
	}
	/**
	 * Defines the possible valid payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD;
	}
	private OrderStatus status=OrderStatus.ORDERED;
	private PaymentMethod method=PaymentMethod.CASH;
	/**
	 * Total order price
	 * @return order price
	 */
	public double Price() {
		return -1.0;
	}
	
	public String getName() {
		return restName;
	}
	
	public Tempo getTime() {
		return tempo;
	}
	public User getUser() {
		return u;
	}
	/**
	 * define payment method
	 * 
	 * @param method payment method
	 */
	public void setPaymentMethod(PaymentMethod method) {
		this.method=method;
	}
	
	/**
	 * get payment method
	 * 
	 * @return payment method
	 */
	public PaymentMethod getPaymentMethod() {
		return this.method;
	}
	
	/**
	 * change order status
	 * @param newStatus order status
	 */
	public void setStatus(OrderStatus newStatus) {
		this.status=newStatus;
	}
	
	/**
	 * get current order status
	 * @return order status
	 */
	public OrderStatus getStatus(){
		return status;
	}
	
	/**
	 * Add a new menu with the relative order to the order.
	 * The menu must be defined in the {@link Food} object
	 * associated the restaurant that created the order.
	 * 
	 * @param menu     name of the menu
	 * @param quantity quantity of the menu
	 * @return this order to enable method chaining
	 */
	public Order addMenus(String menu, int quantity) {
		if(!this.menu.containsKey(menu)) {
			this.menu.put(menu, quantity);
		}else {
			this.menu.remove(menu);
			this.menu.put(menu, quantity);
		}
		return this;
	}
	
	/**
	 * Converts to a string as:
	 * <pre>
	 * RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
	 * 	MENU_NAME_1->MENU_QUANTITY_1
	 * 	...
	 * 	MENU_NAME_k->MENU_QUANTITY_k
	 * </pre>
	 */
	@Override
	public String toString() {
		StringBuffer s=new StringBuffer();
		s.append(restName).append(", ").append(u.getFirstName()).append(" ").append(u.getLastName()).append(" : ").append("(").append(tempo.toString()).append(")").append(":");
		for(String nome:menu.keySet()) {
			s.append("\n\t").append(nome).append("->").append(menu.get(nome));
		}
		return s.toString();
	}
	
}
