package transactions;

public class Request {
	private String id;
	private Place place;
	private String productId;
	private boolean t;
	
	public Request(String id,Place place,String p) {
		this.id=id;
		this.place=place;
		this.productId=p;
		t=false;
	}
	
	public Place getPlace() {
		return place;
	}
	
	public String getId() {
		return id;
	}
	
	public void addTransaction() {
		t=true;
	}
	public boolean getT() {
		return t;
	}
	public String getProductId() {
		return productId;
	}
}
