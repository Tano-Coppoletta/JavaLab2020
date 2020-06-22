package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Course {
	private String name;
	private int posti;
	private List<Student> iscritti=new ArrayList<>();
	
	
	public Course(String name,int posti) {
		this.name=name;
		this.posti=posti;
		
		
	}
	
	public String getName() {
		return name;
	}
	
	public boolean assegnabile() {
		return iscritti.size()<posti;
		
	}
	
	public void iscrivi(Student s) {
		iscritti.add(s);
		s.iscrivi();
		
	}
	
	public List<String> getIscritti(){
		return iscritti.stream().sorted(Comparator.comparing(Student::getMedia).reversed())
				.map(Student::getID).collect(Collectors.toList());
	}
	
	public boolean iscritto(Student s) {
		for(Student s1:iscritti) {
			if(s1.getID().equals(s.getID())) {
				return true;
			}
		}
		return false;
	}
	
	
}
