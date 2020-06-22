package main;
import java.util.*;
import groups.*;

public class Example {

public static void main(String[] args) throws GroupHandlingException {
	GroupHandling gh = new GroupHandling();
//R1
	gh.addProduct("shoes", "s1","s5","s2");
	gh.addProduct("books", "s2","s5","s3");
	gh.addProduct("bikes", "s6","s5","s4","s1");
	List<String> l = gh.getProductTypes("s5");  
		System.out.println(l); //[bikes, books, shoes]
//R2
	gh.addGroup("g1", "books", "c2","c10","c3");
	gh.addGroup("g7", "shoes", "c3","c1","c5");
	gh.addGroup("g8", "bikes", "c10","c1","c3","c7");
	gh.addGroup("g2", "books", "c6","c10","c3","c7");
	l = gh.getGroups("c10");
		System.out.println(l); //[g1, g2, g8]
		try {gh.addGroup("g9","smartphones","s4");}  //unknown product
		catch(GroupHandlingException ex) {System.out.println(ex.getMessage());}
//R3
	gh.setSuppliers("g1","s2","s5");
		try {gh.setSuppliers("g7","s1","s4");}  //s4 unsuitable
		catch(GroupHandlingException ex) {System.out.println(ex.getMessage());}
	gh.setSuppliers("g7","s1","s2");
	gh.setSuppliers("g8","s1","s5","s4","s6");
	gh.addBid("g1", "s2", 1000);
		try {gh.addBid("g7", "s3", 200);} //s3 not in g7
		catch(GroupHandlingException ex) {System.out.println(ex.getMessage());}
	gh.addBid("g7", "s1", 150);
	gh.addBid("g7", "s2", 120);
	gh.addBid("g8", "s5", 800); gh.addBid("g8", "s4", 900);
	gh.addBid("g8", "s1", 900); gh.addBid("g8", "s6", 950);
	String s = gh.getBids("g8");
		System.out.println(s); //s5:800,s1:900,s4:900,s6:950
//R4
	gh.vote("g8", "c1", "s4"); gh.vote("g8", "c7", "s6");
	gh.vote("g8", "c3", "s5"); 
	s = gh.getVotes("g8");
		System.out.println(s);  //s4:1,s5:1,s6:1
	s = gh.getWinningBid("g8");
		System.out.println(s); //s5:1
//R5
	SortedMap<String, Long> m1 =gh.numberOfCustomersPerProductType();
		System.out.println(m1); //{bikes=4, books=7, shoes=3}
	SortedMap<String, Integer> m2 =gh.maxPricePerProductType();
		System.out.println(m2); //{bikes=950, books=1000, shoes=150}
	SortedMap<Integer, List<String>> m3 = gh.suppliersPerNumberOfBids();
		System.out.println(m3); //{2=[s1, s2], 1=[s4, s5, s6]}
}

}
