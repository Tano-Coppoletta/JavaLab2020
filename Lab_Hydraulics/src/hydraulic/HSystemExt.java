package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystemExt extends HSystem{
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		StringBuffer s=new StringBuffer();
		for(Element e:elements) {
			if(e instanceof Source && e!=null) {
				s.append(e.layout(""));
			}
		}
		return s.toString();	
	}
	
	
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public void deleteElement(String name) {
		for(Element e:elements) {
			if(name.equals(e.getName())) {
				if(e instanceof Split || e instanceof Multisplit) {
					System.out.println("non puoi eliminare l'elemento"+name);
					return;
				}
				
				Element out=e.getOutput();
				e.prev.output.remove(0);
				e.prev.output.add(out);
				out.prev=e.prev;
				elements.remove(e);
				break;
				}
		}
		
	}

	/**
	 * starts the simulation of the system; if enableMaxFlowCheck is true,
	 * checks also the elements maximum flows against the input flow
	 */
	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		for(Element e:elements) {
			if(e instanceof Source) {
				if(enableMaxFlowCheck) {
					e.simulazioneExt(observer,-1,enableMaxFlowCheck);
			}
		}
	}
	
}
}
