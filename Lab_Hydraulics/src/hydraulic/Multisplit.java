package hydraulic;

import java.util.ArrayList;

/**
 * Represents a multisplit element, an extension of the Split that allows many outputs
 * 
 * During the simulation each downstream element will
 * receive a stream that is determined by the proportions.
 */

public class Multisplit extends Split {
	int numOutput;
	private double[] proportions;
	/**
	 * Constructor
	 * @param name
	 * @param numOutput
	 */
	public Multisplit(String name, int numOutput) {
		super(name);
		output=new ArrayList<>(numOutput);
		this.numOutput=numOutput;
		proportions=new double[numOutput];
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
    	return output.toArray(new Element[numOutput]);
    }

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){
		output.add(noutput,elem);
	}
	
	/**
	 * Define the proportion of the output flows w.r.t. the input flow.
	 * 
	 * The sum of the proportions should be 1.0 and 
	 * the number of proportions should be equals to the number of outputs.
	 * Otherwise a check would detect an error.
	 * 
	 * @param proportions the proportions of flow for each output
	 */
	public void setProportions(double... proportions) {
		double contatore=0;
		int i=0;
		for(double d:proportions) {
			contatore+=d;
			i++;
		}
		if(contatore!=1) {
			System.out.println("proportions supera 1\n");
			return;
		}
		if(i!=numOutput) {
			System.out.println("troppi proportions\n");
			return;
		}
		
		for(i=0;i<numOutput;i++) {
			this.proportions[i]=proportions[i];
		}
		
	}
	@Override
	public StringBuffer layout(String pad) {
		StringBuffer res=new StringBuffer();
		res.append("[").append(getName()).append("]MultiSplit +->");
		
		String subpad=pad+blanks(res.length()-4);
		
		for(int i=0;i<this.numOutput;i++) {
			if(i>0) {
				res.append("\n");
				res.append(subpad).append("|\n");
				res.append(subpad+"+-> ");
			}
			res.append(getOutputs()[i].layout(subpad+"|   "));
		}
		return res;
		
		
	}
	
	@Override
	void simulazione(SimulationObserver observer,double flow) {
		double[] outFlow=new double[proportions.length];
		for(int i=0;i<proportions.length;i++) {
			outFlow[i]=flow*proportions[i];
		}
		observer.notifyFlow("Split", this.getName(),flow, outFlow);
		int i=0;
		for(Element e: getOutputs()) {
			e.simulazione(observer, outFlow[i]);
			i++;
		}
	}
	@Override
	public void simulazioneExt(SimulationObserverExt observer,double flow,boolean enableMaxFlowCheck) {
		if(enableMaxFlowCheck) {
			observer.notifyFlowError("Multisplit", nome, flow, maxFlow);
		}
		double[] outFlow=new double[proportions.length];
		for(int i=0;i<proportions.length;i++) {
			outFlow[i]=flow*proportions[i];
		}
		observer.notifyFlow("Split", this.getName(),flow, outFlow);
		int i=0;
		for(Element e: getOutputs()) {
			e.simulazioneExt(observer, outFlow[i],enableMaxFlowCheck);
			i++;
		}
		
	}
}
