package diet;

public class Product implements NutritionalElement{
	private String name;
	private double calories;
	private double proteins;
	private double carbs;
	private double fat;
	
	public Product(String name,double calories,double proteins,double carbs,double fat) {
		this.name=name;
		this.calories=calories;
		this.proteins=proteins;
		this.carbs=carbs;
		this.fat=fat;
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getCalories() {
		return this.calories;
	}

	@Override
	public double getProteins() {
		return this.proteins;
	}

	@Override
	public double getCarbs() {
		return this.carbs;
	}

	@Override
	public double getFat() {
		return this.fat;
	}

	@Override
	public boolean per100g() {
		return false;
	}

}
