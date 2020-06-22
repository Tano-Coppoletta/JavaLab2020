package warehouse;

public class Order {
	private String code;
	private int quantity;
	private Product product;
	private Supplier supplier;
	private boolean delivered;
	
	public Order(String code,Product p,Supplier s,int quantity) {
		this.code=code;
		this.product=p;
		this.supplier=s;
		this.quantity=quantity;
		delivered=false;
	}

	public String getCode(){
		return code;
	}
	
	public boolean delivered(){
		return delivered;
	}


	public void setDelivered() throws MultipleDelivery {
		if(delivered)
			throw new MultipleDelivery();
		delivered=true;
		product.setQuantity(quantity+product.getQuantity());
	}
	
	public String toString(){
		return "Order "+code+" for "+quantity+" of "+product.getCode()+" : "+product.getDescription()+" from "+supplier.getNome() ;
	}
	
	public String getProductCode() {
		return product.getCode();
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getSupplierName() {
		return supplier.getNome();
	}
}
