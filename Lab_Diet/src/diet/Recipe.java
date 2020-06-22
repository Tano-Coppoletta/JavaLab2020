package diet;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
    private String name;
    private Food f;
    private Map<NutritionalElement,Double> ingredienti=new LinkedHashMap<>();
    
    public Recipe(String name,Food f) {
    	this.name=name;
    	this.f=f;
    }

	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe addIngredient(String material, double quantity) {
		ingredienti.put(f.getRawMaterial(material), quantity);
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getCalories() {
		Set<NutritionalElement> s1=ingredienti.keySet();
		double val=0,grammi,kcal=0,grammitot=0;
		for(NutritionalElement s:s1) {
			grammi=ingredienti.get(s);
			grammitot+=grammi;
			val=s.getCalories();
			kcal+=(grammi*val)/100;
		}
		return (kcal*100)/grammitot;
	}

	@Override
	public double getProteins() {
		Set<NutritionalElement> s1=ingredienti.keySet();
		double val=0,grammi,kcal=0,grammitot=0;
		for(NutritionalElement s:s1) {
			grammi=ingredienti.get(s);
			grammitot+=grammi;
			val=s.getProteins();
			kcal+=(grammi*val)/100;
		}
		return (kcal*100)/grammitot;
	}

	@Override
	public double getCarbs() {
		Set<NutritionalElement> s1=ingredienti.keySet();
		double val=0,grammi,kcal=0,grammitot=0;
		for(NutritionalElement s:s1) {
			grammi=ingredienti.get(s);
			grammitot+=grammi;
			val=s.getCarbs();
			kcal+=(grammi*val)/100;
		}
		return (kcal*100)/grammitot;
	}

	@Override
	public double getFat() {
		Set<NutritionalElement> s1=ingredienti.keySet();
		double val=0,grammi,kcal=0,grammitot=0;
		for(NutritionalElement s:s1) {
			grammi=ingredienti.get(s);
			grammitot+=grammi;
			val=s.getFat();
			kcal+=(grammi*val)/100;
		}
		return (kcal*100)/grammitot;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}
	
	
	/**
	 * Returns the ingredients composing the recipe.
	 * 
	 * A string that contains all the ingredients, one per per line, 
	 * using the following format:
	 * {@code "Material : ###.#"} where <i>Material</i> is the name of the 
	 * raw material and <i>###.#</i> is the relative quantity. 
	 * 
	 * Lines are all terminated with character {@code '\n'} and the ingredients 
	 * must appear in the same order they have been added to the recipe.
	 */
	@Override
	public String toString() {
		StringBuffer s=new StringBuffer();
		Set<NutritionalElement> s1=ingredienti.keySet();
		for(NutritionalElement s2: s1) {
			s.append(s2.getName()).append(": ").append(ingredienti.get(s2)).append("\n");
		}
		return s.toString();
	}
}
