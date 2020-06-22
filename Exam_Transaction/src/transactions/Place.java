package transactions;

public class Place {
	private String name;
	private Region r;
	
	public Place(String name,Region r) {
		this.name=name;
		this.r=r;
	}
	
	public String getName() {
		return name;
	}
	
	public Region getRegion() {
		return r;
	}
}
