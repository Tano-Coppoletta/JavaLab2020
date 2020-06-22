package hydraulic;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	//private final static int DIMENSIONE=100;
	//public Element[] elementi=new Element[DIMENSIONE];
	//public int index=0;
	protected List<Element> elements=new ArrayList<>();
	
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public void addElement(Element elem){
		elements.add(elem);
	}
	
	/**
	 * returns the element added so far to the system.
	 * If no element is present in the system an empty array (length==0) is returned.
	 * 
	 * @return an array of the elements added to the hydraulic system
	 */
	public Element[] getElements(){
		//Element[] result=new Element[elements.size()];
		//for(int i=0;i<elements.size();i++) {
		//	result[i]=elements.get(i);
		//}
		//return result;
		//toArray serve a creare un vettore con gli elementi della lista, devo passare il vettore da riempire
		return elements.toArray(new Element[elements.size()]);
	}
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		// TODO: to be implemented
		return null;
	}
	
	/**
	 * starts the simulation of the system
	 */
	public void simulate(SimulationObserver observer){
		for(Element e: elements) {
			if(e instanceof Source) {
				e.simulazione(observer,-1);
			}
		}
		
	}

}
