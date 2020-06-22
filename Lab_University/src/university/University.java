package university;

import java.util.logging.Logger;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	protected final static Logger logger = Logger.getLogger("University");
	//informazioni di ateneo
	protected String nome_ateneo;
	protected String nome_rettore;
	protected String cognome_rettore;
	//dichiarazione costante e vettore studenti
	protected final static int NUMERO_MAX_STUDENTI=1000;
	protected Studente[] studenti=new Studente[NUMERO_MAX_STUDENTI];
	private int matricola_attuale=10000;
	private int index_studenti=0;
	//dichiarazione costante e vettore corsi
	private int codice_corso=10;
	private final static int NUMERO_MAX_CORSI=50;
	protected Corso[] corsi=new Corso[NUMERO_MAX_CORSI];
	private int index_corsi=0;
	

	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		this.nome_ateneo=name;
	}
	
	/**
	 * Getter for the name of the university
	 * @return name of university
	 */
	public String getName(){
		return nome_ateneo;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		this.nome_rettore=first;
		this.cognome_rettore=last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return
	 */
	public String getRector(){
		return this.nome_rettore + " " + this.cognome_rettore;
	}
	
	/**
	 * Enroll a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * @return
	 */
	public int enroll(String first, String last){
		if(index_studenti>1000) {
			System.out.println("superati i 1000 studenti");
			return -1;
		}
		Studente s=new Studente(first,last,matricola_attuale);
		studenti[index_studenti]=s;
		index_studenti++;
		matricola_attuale++;
		logger.info("New student enrolled:"+matricola_attuale+ ", "+ first+" "+last);
		
		return matricola_attuale-1;
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the id of the student
	 * @return information about the student
	 */
	public String student(int id){
		for(Studente s:studenti) {
			if(s.confronta_matricola(id)) {
				return s.toString();
			}
		}
		return null;
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		if(index_corsi>50) {
			System.out.println("superati i 50 corsi");
			return -1;
		}
		Corso c=new Corso(teacher,title,codice_corso);
		corsi[index_corsi]=c;
		index_corsi++;
		codice_corso++;
		logger.info("New course activated:"+codice_corso+" "+title+" "+teacher);
		return codice_corso-1;
	}
	
	/**
	 * Retrieve the information for a given course
	 * 
	 * @param code unique code of the course
	 * @return information about the course
	 */
	public String course(int code){
		for(Corso c:corsi) {
			if(c.confronta_codice(code)) {
				return c.toString();
			}
		}
		return null;
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		for(Corso c:corsi) {
			if(c.confronta_codice(courseCode)) {
				for(Studente s:studenti) {
					if(s.confronta_matricola(studentID)) {
						s.iscrizione(c);
						c.iscrizione(s);
						break;
					}
				}
				break;
			}
		}
		logger.info("Student "+studentID+" signed up for course "+ courseCode);
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		for(Corso c:corsi) {
			if(c.confronta_codice(courseCode)) {
				return c.iscritti();
			}
		}
		
		return null;
	}

	/**
	 * Retrieves the study plan for a student
	 * 
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		for(Studente s:studenti) {
			if(s.confronta_matricola(studentID)) {
				return s.corsi();
			}
		}
		return null;
	}
}
