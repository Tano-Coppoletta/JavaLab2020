package delivery;

public class Item {
	private String descrizione;
	private double price;
	private String categoria;
	private int preptime;
	
	public Item(String descrizione,double price,String categoria,int preptime) {
		this.descrizione=descrizione;
		this.price=price;
		this.categoria=categoria;
		this.preptime=preptime;
	}
	
	public String getDescription() {
		return descrizione;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public boolean search(String s) {
		if(descrizione.toLowerCase().contains(s.toLowerCase()))
			return true;
		return false;
			
	}
	
	public int getTime() {
		return preptime;
	}
	
	@Override
	public String toString() {
		return "["+categoria+"] "+descrizione+" : "+String.format("%.2f", price);
	}
}
