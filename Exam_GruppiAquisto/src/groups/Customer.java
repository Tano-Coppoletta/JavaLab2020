package groups;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer {
	private String name;
	private List<Group> groups=new ArrayList<>();
	private Bid bid;
	
	public Customer(String s) {
		name=s;
	}
	public String getName() {
		return name;
	}
	public void addGroup(Group g) {
		groups.add(g);
	}
	
	public List<String> getGroups(){
		return groups.stream().map(Group::getName).sorted().collect(Collectors.toList());
	}
	
	public List<Group> getGroup(){
		return groups;
	}
	
	public boolean Gruppook(Group g) {
		for(Group s:groups) {
			if(s.getName().equals(g.getName()))
				return true;
		}
		return false;
	}
	public void addBid(Bid b) {
		bid=b;
	}
}
