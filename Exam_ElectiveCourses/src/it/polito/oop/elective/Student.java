package it.polito.oop.elective;


import java.util.List;

public class Student {
	private String id;
	private double media;
	private List<Course> preferenze;
	private boolean iscritto=false;
	
	public Student(String id,double media) {
		this.id=id;
		this.media=media;
	}
	
	public boolean compresa(double inf,double sup) {
		if(media>=inf && media <=sup)
			return true;
		return false;
	}
	
	public String getID() {
		return this.id;
	}
	public void addPreference(List<Course> s) {
		preferenze=s;
	}
	public int numScelta(String s) {
		for(int i=0;i<preferenze.size();++i) {
			if(preferenze.get(i).getName().equals(s))
				return i+1;
		}
		return -1;
	}
	
	public double getMedia() {
		return media;
	}
	public Course getScelta(int i) {
		if(preferenze.size()>=i) {
			return preferenze.get(i-1);
			}
		return null;
	}
	
	public List<Course> getPreferenze(){
		return preferenze;
	}
	public void iscrivi() {
		iscritto=true;
	}
	
	public boolean eIscritto() {
		return iscritto;
	}
}
