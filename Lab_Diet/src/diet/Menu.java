package diet;


import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	private String name;
	private Food f;
	private Map<NutritionalElement,Double> ricette=new LinkedHashMap<>();
	private Set<NutritionalElement> prodotti=new LinkedHashSet<>();
	
	
	public Menu(String name,Food f) {
		this.name=name;
		this.f=f;
	}
	/**
	 * Adds a given serving size of a recipe.
	 * 
	 * The recipe is a name of a recipe defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
	public Menu addRecipe(String recipe, double quantity) {
		ricette.put(f.getRecipe(recipe), quantity);
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
		prodotti.add(f.getProduct(product));
		return this;
	}

	/**
	 * Name of the menu
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		Set<NutritionalElement> s1=ricette.keySet();
		double val=0,r=0,grammi=0;
		for(NutritionalElement s:s1) {
			r=s.getCalories();
			grammi=ricette.get(s);
			val+=(r*grammi)/100;
		}
		
		for(NutritionalElement s:prodotti) {
			val+=s.getCalories();
		}
		return val;	
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		Set<NutritionalElement> s1=ricette.keySet();
		double val=0,r=0,grammi=0;
		for(NutritionalElement s:s1) {
			r=s.getProteins();
			grammi=ricette.get(s);
			val+=(r*grammi)/100;
		}
		for(NutritionalElement s:prodotti) {
			val+=s.getProteins();
		}
		return val;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		Set<NutritionalElement> s1=ricette.keySet();
		double val=0,r=0,grammi=0;
		for(NutritionalElement s:s1) {
			r=s.getCarbs();
			grammi=ricette.get(s);
			val+=(r*grammi)/100;
		}
		for(NutritionalElement s:prodotti) {
			val+=s.getCarbs();
		}
		return val;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		Set<NutritionalElement> s1=ricette.keySet();
		double val=0,r=0,grammi=0;
		for(NutritionalElement s:s1) {
			r=s.getFat();
			grammi=ricette.get(s);
			val+=(r*grammi)/100;
		}
		for(NutritionalElement s:prodotti) {
			val+=s.getFat();
		}
		return val;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean 	indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
}
