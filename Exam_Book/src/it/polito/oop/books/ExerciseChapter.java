package it.polito.oop.books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ExerciseChapter {
	private String title;
	private int numPages;
	private List<Question> question=new ArrayList<>();

    public ExerciseChapter(String title, int numPages) {
		this.title=title;
		this.numPages=numPages;
	}


	public List<Topic> getTopics() {
		return question.stream().map(Question::getMainTopic).distinct().sorted().collect(Collectors.toList());
		
	};
	

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
    	this.title=newTitle;
    }

    public int getNumPages() {
        return numPages;
    }
    
    public void setNumPages(int newPages) {
    	numPages=newPages;
    }
    

	public void addQuestion(Question question) {
		this.question.add(question);
	}	
}
