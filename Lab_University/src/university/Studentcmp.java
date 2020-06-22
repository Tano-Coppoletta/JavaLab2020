package university;

import java.util.Comparator;


public class Studentcmp implements Comparator<Object>{
	@Override
	public int compare(Object o1, Object o2) {	
		Studente sa=(Studente)o1;
		Studente sb=(Studente)o2;
		if(sa==null || sb==null)
			return 0;
		float punti1=sa.calcola_media()+sa.bonus();
		float punti2=sb.calcola_media()+sa.bonus();
		float diff=(punti2-punti1);
		if(diff>0){
			return 1;
		}else if(diff<0)
			return(-1);
		else return 0;
		}

}
