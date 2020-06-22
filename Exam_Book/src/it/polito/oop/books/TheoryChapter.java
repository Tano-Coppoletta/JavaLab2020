package it.polito.oop.books;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class TheoryChapter {
	private String title;
	private int numpages;
	private String testo;
	private Set<Topic> topic=new TreeSet<>();
	
	
	public TheoryChapter(String title,int nump,String testo) {
		this.title=title;
		this.numpages=nump;
		this.testo=testo;
	}

    public String getText() {
		return testo;
	}

    public void setText(String newText) {
    	testo=newText;
    }


	public List<Topic> getTopics() {
        return topic.stream().collect(Collectors.toList());
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
    	title=newTitle;
    }

    public int getNumPages() {
        return numpages;
    }
    
    public void setNumPages(int newPages) {
    	numpages=newPages;
    }
    public boolean present(Topic t) {
    	if(topic.contains(t))
    		return true;
    	return false;
    }
    
    public void addTopic(Topic topic) {
    	if(!this.topic.contains(topic))
    		this.topic.add(topic);
    	for(Topic t:topic.getSubTopics()) {
    		addTopic(t);
    	}
    }
}
