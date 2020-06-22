package university;

import java.util.Arrays;


public class UniversityExt extends University {
	
	

	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	}
	
	public void exam(int studentId, int courseID, int grade) {
		if(grade>30 || grade<0)
			return;
		for(Studente stud: studenti) {
			if(stud.confronta_matricola(studentId)) {
				stud.voto(courseID,grade);
				break;
			}
		}
		for(Corso c: corsi) {
			if(c.confronta_codice(courseID)) {
				c.voto(studentId, grade);
				break;
			}
		}
		logger.info("Student "+studentId +" took an exam in course "+ courseID + " with grade " + grade);
	}

	public String studentAvg(int studentId) {
		float media;
		for(Studente s:studenti) {
			if(s.confronta_matricola(studentId)) {
				if(s.almenoUnEsame()) {
					media=s.calcola_media();
					return "Student" + " " + studentId + ": " + media;
				}else {
					return "Student"+ studentId + "hasn't taken any exams";
				}
				
			}
		}
		return null;
	}
	
	public String courseAvg(int courseId) {
		float media;
		for(Corso c:corsi) {
			if(c.confronta_codice(courseId)) {
				if(c.Almenouno()) {
					media=c.media();
					return "The average for the course " + c.getNome() + " is: " + media;
				}else {
					return "No student has taken the exam in " + c.getNome(); 
				}
			}
		}
		return null;
	}
	
	public String topThreeStudents() {
		Arrays.sort(studenti,new Studentcmp());
		String s="";
		float punti=0;
		for(int i=0;i<3;i++) {
			if(studenti[i]!=null) {
				punti=(studenti[i].calcola_media()+studenti[i].bonus());
				if(punti>0)
					s=s+studenti[i].TopTre()+" "+ punti +"\n";
			}
		}
		return s;
	}
}
