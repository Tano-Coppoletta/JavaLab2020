package hydraulic;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public abstract class Element {
	protected String nome;
	protected List<Element> output;
	protected Element prev;
	/**
	 * Constructor
	 * @param name the name of the element
	 */
	public Element(String name){
		this(name,1);
		
	}
	
	public Element(String name,int size){
		this.nome=name;
		output=new ArrayList<>(size);
		
	}

	/**
	 * getter method
	 * @return the name of the element
	 */
	public String getName(){
		return this.nome;
	}
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem){
		connect(elem,0);
		elem.prev=this;;
	}
	
	/**
	 * Retrieves the element connected downstream of this
	 * @return downstream element
	 */
	public Element getOutput(){
		if( output.get(0)!=null)
			return output.get(0);
		else return null;
	}
	
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
    	return output.toArray(new Element[output.size()]);
    }

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){
		output.add(noutput, elem);
	}
	
	abstract void simulazioneExt(SimulationObserverExt observer,double flow,boolean enableMaxFlowCheck);
	abstract void simulazione(SimulationObserver observer,double flow);


	public Element delete(String name) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public void setMaxFlow(double maxFlow) {
		// TODO Auto-generated method stub
		
	}
	public StringBuffer layout(String pad) {
		return null;
		
	}
}
