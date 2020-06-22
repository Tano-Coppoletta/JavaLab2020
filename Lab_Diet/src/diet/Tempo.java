package diet;

public class Tempo {
	private int ore;
	private int min;
	
	public Tempo(int ora,int minuti) {
		ora=ore;
		min=minuti;
	}
	
	public Tempo(String ora) {
		String[] s=ora.split(":");
		ore=Integer.parseInt(s[0]);
		min=Integer.parseInt(s[1]);
	}
	
	public String toString() {
		return String.format("%02d:%02d", ore,min);
	}
	public int compareTo(String s) {
		String[] s1=s.split(":");
		int o=Integer.parseInt(s1[0]);
		int m=Integer.parseInt(s1[1]);
		if(ore!=o) {
			return ore-o;
		}else if(min!=m) {
			return min-m;
		}
		return 0;
	}
	public int compareTo(Tempo t2) {
		if(ore!=t2.ore)
			return ore-t2.ore;
		else if(min!=t2.min)
			return min-t2.min;
		return 0;
	}
}
