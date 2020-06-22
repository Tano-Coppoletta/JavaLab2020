package university;

public class Corso {
	private String nome_docente;
	private String nome_corso;
	private int codice;
	private final static int NUMERO_MAX_ISCRITTI=100;
	private Studente[] iscritti=new Studente[NUMERO_MAX_ISCRITTI];
	private int[] voti=new int[NUMERO_MAX_ISCRITTI];
	private int index=0;
	private boolean almenoUno=false;
	
	
	Corso(String nome_docente,String nome_corso,int codice){
		this.nome_docente=nome_docente;
		this.nome_corso=nome_corso;
		this.codice=codice;
	}
	
	public boolean confronta_codice(int cod) {
		if(this.codice==cod)
			return true;
		return false;
	}
	
	
	public String toString() {
		return codice + ", " + nome_corso + ", " + nome_docente;
	}
	
	public void iscrizione(Studente stud) {
		if(index>=100) {
			System.out.println("superati i 100 iscritti per il corso "+ codice);
			return;
		}
		iscritti[index]=stud;
		index++;
		
	}
	
	public String iscritti() {
		String s="";
		for(Studente stud:iscritti) {
			if(stud==null)
				break;
			s+=stud.toString();
			s+="\n";
		}
		return s;
	}
	public void voto(int studentID,int voto) {
		int i=0;
		for(Studente s: iscritti) {
			if(s==null)
				break;
			if(s.confronta_matricola(studentID)) {
				voti[i]=voto;
				almenoUno=true;
				break;
			}
			i++;
		}
	}
	public boolean Almenouno() {
		return almenoUno;
	}
	
	public float media() {
		int i=0;
		float tot=0;
		for(int v: voti) {
			if(v!=0) {
				tot+=v;
				i++;
			}
		}
		return tot/i;
	}
	public String getNome() {
		return nome_corso;
	}
}
