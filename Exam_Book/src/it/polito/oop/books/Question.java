package it.polito.oop.books;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Question {
	private Topic topic;
	private String testo;
	private Map<String,Boolean> answers=new HashMap<>();
	
	
	public Question(String testo,Topic t) {
		this.topic=t;
		this.testo=testo;
	}
	
	public String getQuestion() {
		return testo;
	}
	
	public Topic getMainTopic() {
		return topic;
	}

	public void addAnswer(String answer, boolean correct) {
		answers.put(answer, correct);
		
	}
	
    @Override
    public String toString() {
        return testo+" ("+topic.toString()+")";
    }

	public long numAnswers() {
	    return answers.size();
	}

	public Set<String> getCorrectAnswers() {
		Set <String> s=new HashSet<>();
		for(String s1:answers.keySet()) {
			if(answers.get(s1))
				s.add(s1);
		}
		return s;
	}

	public Set<String> getIncorrectAnswers() {
		Set <String> s=new HashSet<>();
		for(String s1:answers.keySet()) {
			if(!answers.get(s1))
				s.add(s1);
		}
		return s;
	}
}
