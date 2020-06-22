package it.polito.oop.books;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Topic implements Comparable<Topic>{
	private String name;
	private Set<Topic> subtopic=new TreeSet<>() ;
	
	@Override
	public int compareTo(Topic o) {
		return name.compareTo(o.name);
	}
	
	public Topic(String name) {
		this.name=name;
	}

	public String getKeyword() {
        return name;
	}
	
	@Override
	public String toString() {
	    return name;
	}

	public boolean addSubTopic(Topic topic) {
		if(subtopic.contains(topic)) {
			return false;
		}
		subtopic.add(topic);
        return true;
	}

	/*
	 * Returns a sorted list of subtopics. Topics in the list *MAY* be modified without
	 * affecting any of the Book topic.
	 */
	public List<Topic> getSubTopics() {
		return subtopic.stream().collect(Collectors.toList());
        
	}
}
