package it.polito.oop.books;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Book {
	private Map<String,Topic> topics=new HashMap<>();
	private List<Question> question=new ArrayList<>();
	private List<TheoryChapter> teoria=new ArrayList<>();
	private List<ExerciseChapter> esercizi=new ArrayList<>();
    /**
	 * Creates a new topic, if it does not exist yet, or returns a reference to the
	 * corresponding topic.
	 * 
	 * @param keyword the unique keyword of the topic
	 * @return the {@link Topic} associated to the keyword
	 * @throws BookException
	 */
	public Topic getTopic(String keyword) throws BookException {
		if(keyword==null || keyword.equals("")) {
			throw new BookException();
		}
		if(topics.containsKey(keyword))
			return topics.get(keyword);
		Topic t=new Topic(keyword);
		topics.put(keyword,t);
	    return t;
	}

	public Question createQuestion(String question, Topic mainTopic) {
		Question q=new Question(question,mainTopic);
		this.question.add(q);
        return q;
	}

	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
		TheoryChapter t=new TheoryChapter(title,numPages,text);
		teoria.add(t);
        return t;
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
		ExerciseChapter e=new ExerciseChapter(title,numPages);
		esercizi.add(e);
        return e;
	}

	public List<Topic> getAllTopics() {
		return topics.values().stream().distinct().sorted().collect(Collectors.toList());
	}

	public boolean checkTopics() {
		Set<Topic> t=teoria.stream().flatMap(x->x.getTopics().stream()).collect(Collectors.toSet());
		Set<Topic> e=esercizi.stream().flatMap(x->x.getTopics().stream()).collect(Collectors.toSet());
        return t.containsAll(e);
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
        return new Assignment(ID,chapter);
       
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
    	return question.stream().collect(Collectors.groupingBy(
    			q->q.numAnswers(),Collectors.toList()));
    }
}
