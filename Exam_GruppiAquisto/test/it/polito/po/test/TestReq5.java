package it.polito.po.test;
import java.util.List;
import java.util.SortedMap;

import junit.framework.TestCase;
//import java.util.*;
import groups.*;

public class TestReq5 extends TestCase {
    GroupHandling gh;
    protected void setUp() throws Exception {
        gh = new GroupHandling();
		gh.addProduct("beta", "f1","f3","f2");
		gh.addProduct("alfa", "f2","f5","f3");
		gh.addProduct("gamma", "f6","f3","f4","f1");
		gh.addGroup("betaGroup", "beta", "q5","p3","p2");
		gh.addGroup("betaGroup1", "beta", "q5","p3","p2");
		gh.addGroup("alfaGroup", "alfa", "q4","p3","p1");
		gh.addGroup("gammaGroup", "gamma", "p2","p3","p1","p4");
		gh.setSuppliers("gammaGroup","f4","f3","f1","f6");
		gh.setSuppliers("betaGroup","f1","f3","f2");
		gh.addBid("gammaGroup", "f4", 400);
		gh.addBid("gammaGroup", "f3", 300);
		gh.addBid("gammaGroup", "f1", 400);
		gh.addBid("betaGroup", "f2", 800);
		gh.addBid("betaGroup", "f3", 700);
		gh.addBid("betaGroup", "f1", 600);
		gh.vote("gammaGroup", "p1", "f3");
		gh.vote("gammaGroup", "p2", "f1");
		gh.vote("gammaGroup", "p3", "f1");
		gh.vote("gammaGroup", "p4", "f3");
	}
	
	public void testMaxPricePerProductType() {
			SortedMap<String, Integer> map =gh.maxPricePerProductType();
			assertNotNull(map);
			String result = map.toString();
			//System.out.println(result); //{beta=800, gamma=400}
			String s = "{beta=800, gamma=400}";
            assertNotNull(result);
            assertEquals(s,result);
	}
	
	public void testSuppliersPerNumberOfBids() {
			SortedMap<Integer, List<String>> map = gh.suppliersPerNumberOfBids();
			assertNotNull(map);
			String result = map.toString();
			//System.out.println(result); //{2=[f1, f3], 1=[f2, f4]}
			String s = "{2=[f1, f3], 1=[f2, f4]}";
            assertNotNull(result);
            assertEquals(s,result);
	}
	
	public void testNumberOfCustomersPerProductType() {
			SortedMap<String, Long> map =gh.numberOfCustomersPerProductType();
			assertNotNull(map);
			String result = map.toString(); 
			//System.out.println(result); // {alfa=3, beta=6, gamma=4}
			String s = "{alfa=3, beta=6, gamma=4}";
            assertNotNull(result);
            assertEquals(s,result);
	}
	
}
