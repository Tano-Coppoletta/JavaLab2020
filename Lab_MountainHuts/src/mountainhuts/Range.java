package mountainhuts;

public class Range {
	private int min;
	private int max;
	
	public Range(int min,int max) {
		this.min=min;
		this.max=max;
	}
	public Range(String s) {
		String s1[]=s.split("-");
		min=Integer.parseInt(s1[0]);
		max=Integer.parseInt(s1[1]);
	}
	public boolean incluso(Integer i) {
		if(i>=min) {
			if(i<=max) 
				return true;
		}
		return false;
	}
	public String toString() {
		return min+"-"+max;
	}
	
}
