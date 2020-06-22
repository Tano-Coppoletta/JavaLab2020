package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends ElementExt {

	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
	}
	
	@Override
	public Element getOutput(){
		return null;
	}
	@Override
	public void connect(Element elem){
		//non fa nulla
	}
	
	@Override
	void simulazione(SimulationObserver observer,double flow) {
		observer.notifyFlow("Sink", this.getName(), flow, SimulationObserver.NO_FLOW);
	}
	@Override
	public void simulazioneExt(SimulationObserverExt observer,double flow,boolean enableMaxFlowCheck) {
		if(enableMaxFlowCheck) {
			if(flow>maxFlow)
				observer.notifyFlowError("Sink", nome, flow, maxFlow);
		}
		observer.notifyFlow("Sink", this.getName(), flow, SimulationObserver.NO_FLOW);
	}
	
	@Override
	public StringBuffer layout(String pad) {
		StringBuffer res=new StringBuffer();
		res.append("[").append(getName()).append("]Sink *");
		return res;
		
	}
	
}
