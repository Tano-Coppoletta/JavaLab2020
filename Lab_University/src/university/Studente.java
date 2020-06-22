package university;


public class Studente{
	private String nome;
	private String cognome;
	private int matricola;
	private final static int NUMERO_MAX_ISCRIZIONI=25;
	private Corso[] corsi=new Corso[NUMERO_MAX_ISCRIZIONI];
	private int index=0;
	private int[] voti=new int[NUMERO_MAX_ISCRIZIONI];
	private int EsamiSostenuti=0;
	
	
	Studente(String nome,String cognome,int matricola){
		this.nome=nome;
		this.cognome=cognome;
		this.matricola=matricola;
	}
	
	public boolean confronta_matricola(int matricola) {
		if(this.matricola==matricola)
			return true;
		return false;
	}
	public String toString() {
		return matricola + " " + nome + " " + cognome;
	}
	
	public void iscrizione(Corso corso) {
		if(index>=25) {
			System.out.println("superati i 25 corsi per lo studente " + matricola);
			return;
		}
		corsi[index]=corso;
		index++;
	}
	
	public String corsi() {
		String c="";
		for(Corso corso:corsi) {
			if(corso==null)
				break;
			c+=corso.toString();
			c+="\n";
		}
		return c;
	}
	
	void voto(int courseID,int voto) {
		int i=0;
		for(Corso corso: corsi) {
			if(corso==null)
				break;
			if(corso.confronta_codice(courseID)) {
				voti[i]=voto;
				EsamiSostenuti++;
				break;
			}
			i++;
		}
	}
	public float calcola_media() {
		int tot=0,i=0;
		for(int v:voti) {
			if(v!=0) {
				tot+=v;
				i++;
			}
		}
		if(i!=0)
			return tot/i;
		else return 0;
	}
	
	public boolean almenoUnEsame() {
		if(EsamiSostenuti!=0)
			return true;
		return false;
	}

	public float bonus() {
		if(index!=0)
			return (EsamiSostenuti/index)*10;
		return 0;
	}
	
	public String TopTre() {
		return nome + " "+ cognome;
	}
}
