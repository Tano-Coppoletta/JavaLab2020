package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends ElementExt {
	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name,2);
	}
	@Override
	void simulazione(SimulationObserver observer,double flow) {
		double outFlow=flow/2;
		observer.notifyFlow("Split", this.getName(),flow, outFlow,outFlow);
		for(Element e: getOutputs()) {
			e.simulazione(observer, outFlow);
		}
	}
	
	@Override
	public void simulazioneExt(SimulationObserverExt observer,double flow,boolean enableMaxFlowCheck) {
		if(enableMaxFlowCheck) {
			observer.notifyFlowError("Split", nome, flow, maxFlow);
		}
		double outFlow=flow/2;
		observer.notifyFlow("Split", this.getName(),flow, outFlow,outFlow);
		for(Element e: getOutputs()) {
			e.simulazioneExt(observer, outFlow,enableMaxFlowCheck);
		}
	}
	@Override
	public StringBuffer layout(String pad) {
		StringBuffer res=new StringBuffer();
		res.append("[").append(getName()).append("]Split +->");
		
		String subpad= pad+ blanks(res.length()-4);
		
		res.append(getOutputs()[0].layout(subpad+"| "));
		
		res.append("\n");
		res.append(subpad).append("|\n");
		res.append(subpad+ "+-> ");
		res.append(getOutputs()[1].layout(subpad+"    "));
		return res;
	}
}
