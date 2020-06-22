package it.polito.oop.books;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Assignment {
	private String matricola;
	private ExerciseChapter e;
	private Map<Question,Double> scores=new HashMap<>();
	
	public Assignment(String s,ExerciseChapter e) {
		this.matricola=s;
		this.e=e;
	}

    public String getID() {
        return matricola;
    }

    public ExerciseChapter getChapter() {
        return e;
    }

    public double addResponse(Question q,List<String> answers) {
    	Set<String> correct=q.getCorrectAnswers();
    	Set<String> incorrect=q.getIncorrectAnswers();
    	
    	double n=correct.size() + incorrect.size();
    	
    	double fp=answers.stream().filter(x->!correct.contains(x)).count();
    	
    	double fn=correct.stream().filter(x->!answers.contains(x)).count();
    	
    	double score=(n-fp-fn)/n;
    	
    	scores.put(q,score);
    	
        return score;
    }
    
    public double totalScore() {
    	return scores.values().stream().mapToDouble(s->s).sum();
    	
    }

}
