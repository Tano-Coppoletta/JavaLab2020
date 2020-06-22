package clinic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {
	private Map<String,Patient> patients=new HashMap<>();
	private Map<Integer,Doctor> doctors=new HashMap<>();
	
	

	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	public void addPatient(String first, String last, String ssn) {
		patients.put(ssn, new Patient(first,last,ssn));
	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		if(!patients.containsKey(ssn)) {
			throw new NoSuchPatient("paziente non presente");
		}
		return patients.get(ssn).toString();
	}
	
	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		Doctor d=new Doctor(first,last,ssn,docID,specialization);
		doctors.put(docID, d);
		patients.put(ssn,d);

	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		if(!doctors.containsKey(docID)) {
			throw new NoSuchDoctor("dottore non presente");
		}
		return doctors.get(docID).toString();
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		if(!patients.containsKey(ssn))
			throw new NoSuchPatient("paziente non esistente");
		if(!doctors.containsKey(docID))
			throw new NoSuchDoctor("dottore non esiste");
		patients.get(ssn).addDoc(doctors.get(docID));
		doctors.get(docID).addPatient(patients.get(ssn));
	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		if(!patients.containsKey(ssn))
			throw new NoSuchPatient("paziente non esiste");
		if(!patients.get(ssn).isPresent()) {
			throw new NoSuchDoctor("dottore assente");
		}
		
		return patients.get(ssn).getDoc().getID();
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		if(!doctors.containsKey(id))
			throw new NoSuchDoctor("dottore non presente");
		return doctors.get(id).getLista();
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and specialization.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param readed linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public void loadData(Reader reader) throws IOException {
//		List<String> righe=new ArrayList<>();
//		try (BufferedReader in=new BufferedReader(reader)){
//			righe=in.lines().collect(Collectors.toList());
//		}catch(IOException e) {
//			System.err.println(e.getMessage());
//			throw e;
//		}
//		String[] s1;
//		for(String s:righe) {
//			if(s.charAt(0)=='P') {
//				s1=s.split(" ; ");
//				patients.put(s1[2],new Patient(s1[0],s1[1],s1[2]));
//			}else if(s.charAt(0)=='D') {
//				s1=s.split(" ; ");
//				doctors.put(Integer.parseInt(s1[0]), new Doctor(s1[0],s1[1],s1[2],Integer.parseInt(s1[3]),s1[4]));
//			}
//		}
		BufferedReader br=new BufferedReader(reader);
		
		br.lines().forEach(line->{
				String s[]=line.split("\\s*;\\s*");
				
				try {	
					if(s.length>0) {
						if(s[0].equals("P")) {
							patients.put(s[2],new Patient(s[0],s[1],s[2]));
						
					}else if(s[0].equals("D")) {
						try {
							doctors.put(Integer.parseInt(s[0]), new Doctor(s[0],s[1],s[2],Integer.parseInt(s[3]),s[4]));
						}catch(NumberFormatException n) {
							
						}
						}
				}
				}catch(ArrayIndexOutOfBoundsException e) {
					//niente
				}
				
		});
		
	}


	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */
	public Collection<Integer> idleDoctors(){
		return doctors.values().stream()
			.filter(s->{return s.getLista().size()==0;})
			.sorted((a,b)->{
				int cmp=a.getLast().compareTo(b.getLast());
				if(cmp!=0)
					return cmp;
				cmp=a.getName().compareTo(b.getName());
				if(cmp!=0)
					return cmp;
				return 0;
			})
			.map(Doctor::getID)
			.collect(Collectors.toList());
		
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors' ids
	 */
	public Collection<Integer> busyDoctors(){
		return doctors.values().stream()
				.filter(s->!s.getLista().isEmpty())
				.collect(Collectors.collectingAndThen(Collectors.averagingInt(Doctor::getSize)
					,
					(a)->{
						return
						doctors.values().stream()
						.filter(s-> !s.getLista().isEmpty())
						.filter(s->{return s.getSize()>a;})	
						.map(Doctor::getID)
						.collect(Collectors.toList());
					}));	
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
		return doctors.values().stream()
			.sorted((a,b)->{
				int cmp=a.getSize()-b.getSize();
				if(cmp!=0)
					return cmp;
				return cmp;
			})
			.map(Doctor::bella)
			.collect(Collectors.toList());
		
	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization(){
		return patients.values().stream()
			.collect(Collectors.groupingBy(s->{return s.getDoc().getSpec();},Collectors.counting()))
			.entrySet().stream()
			.sorted((a,b)->{
				return a.getValue().compareTo(b.getValue());
			})
			.sorted((a,b)->{
				return a.getKey().compareTo(b.getKey());
			})
			.map(s->String.format("%3d", s.getValue())+s.getKey())
			.collect(Collectors.toSet());
		
	}
	
}
