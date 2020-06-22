package hydraulic;

import java.util.Arrays;

public abstract class ElementExt extends Element{
	protected double maxFlow;
	public ElementExt(String name) {
		super(name);
	}
	public ElementExt(String name,int size){
		super(name,size);		
	}

	public void setMaxFlow(double maxFlow) {
		this.maxFlow=maxFlow;
	}
	protected static String blanks(int n) {
		char[] res=new char[n];
		Arrays.fill(res,' ');
		return new String(res);
	}

}
