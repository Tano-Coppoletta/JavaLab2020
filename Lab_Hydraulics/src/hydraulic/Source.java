package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * The status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends ElementExt {
	private double flow;
	public Source(String name) {
		super(name);
	}

	/**
	 * defines the flow produced by the source
	 * 
	 * @param flow
	 */
	public void setFlow(double flow){
		this.flow=flow;
		
	}
	@Override
	public void simulazioneExt(SimulationObserverExt observer,double flow,boolean enableMaxFlowCheck) {
		observer.notifyFlow("Source",this.getName(),SimulationObserver.NO_FLOW,this.flow);
		getOutput().simulazioneExt(observer, this.flow,enableMaxFlowCheck);
	}
	
	@Override
	void simulazione(SimulationObserver observer,double flow) {
		observer.notifyFlow("Source",this.getName(),SimulationObserver.NO_FLOW,this.flow);
		getOutput().simulazione(observer, this.flow);
	}
	
	@Override
	public StringBuffer layout(String pad){
		StringBuffer res=new StringBuffer();
		res.append("[").append(getName()).append("] Source -> "); 
		res.append(getOutput().layout(blanks(res.length())));
		return res;
	}
	@Override
	public void setMaxFlow(double maxFlow) {
	}

	


}
