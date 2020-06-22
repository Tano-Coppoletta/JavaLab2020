package groups;

public class Bid {
	private Group group;
	private Supplier supplier;
	private int price;
	
	public Bid(Group group,Supplier s,int price) {
		this.group=group;
		this.supplier=s;
		this.price=price;
	}
	public Group getGroup() {
		return group;
	}
	
	public String getProductName() {
		return group.getProduct().getName();
	}
	
	public int Max() {
		return group.getMaxPrice();
	}
	
	public String getSupplierName() {
		return supplier.getName();
	}
	
	public int getPrice() {
		return price;
	}
	
	public String toString() {
		return supplier.getName()+":"+price;
	}
}
