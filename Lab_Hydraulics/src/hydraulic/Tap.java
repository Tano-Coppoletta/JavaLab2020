package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends ElementExt {
	private boolean open;

	public Tap(String name) {
		super(name);
	}
	
	/**
	 * Defines whether the tap is open or closed.
	 * 
	 * @param open  opening level
	 */
	public void setOpen(boolean open){
		this.open=open;
	}
	
	@Override
	void simulazione(SimulationObserver observer,double flow) {
		if(open) {
			observer.notifyFlow("Tap",this.getName(),flow,flow);
			getOutput().simulazione(observer,flow);
		}else {
			double outFlow=open ? flow:0.0;
			observer.notifyFlow("Tap",this.getName(),flow,outFlow);
			getOutput().simulazione(observer,outFlow);
		}
		
	}
	@Override
	public void simulazioneExt(SimulationObserverExt observer,double flow,boolean enableMaxFlowCheck) {
		if(enableMaxFlowCheck) {
			if(flow>maxFlow) {
				observer.notifyFlowError("Tap",nome,flow,maxFlow);
			}
		}
		double outFlow=open ? flow:0.0;
		observer.notifyFlow("Tap",this.getName(),flow,outFlow);
		getOutput().simulazioneExt(observer,outFlow,enableMaxFlowCheck);
	}
	
	@Override
	public StringBuffer layout(String pad) {
		StringBuffer res=new StringBuffer();
		res.append("[").append(getName()).append("]Tap -> ");
		res.append(getOutput().layout(pad+blanks(res.length())));
		return res;
		
	}
	

}
