package it.polito.po.test;
import junit.framework.TestCase;
import java.util.*;
import groups.*;

public class TestReq2 extends TestCase {
    GroupHandling gh;
    protected void setUp() throws Exception {
        gh = new GroupHandling();
		gh.addProduct("beta", "f1","f3","f2");
		gh.addProduct("alfa", "f2","f5","f3");
		gh.addProduct("gamma", "f6","f3","f4","f1");
		gh.addGroup("betaGroup", "beta", "q5","p3","p2");
		gh.addGroup("alfaGroup", "alfa", "q4","p3","p1");
	}
	
	public void testAddGroup() {
		try {
			gh.addGroup("alfaGroup", "beta", "q3","q1");
			fail("group duplicated");
		} catch (Exception e) {
			//OK
		}
	}
	
	public void testAddGroup2() {
		try {
			gh.addGroup("deltaGroup", "delta", "q3","q1");
			fail("unknown product");
		} catch (Exception e) {
			// OK
		}
	}
		
	public void testGetGroups() {
			List<String> list = gh.getGroups("p3");  
			assertNotNull(list);
			//System.out.println(list);
			String s = "[alfaGroup, betaGroup]";
			assertEquals(s,list.toString());
	}
	
	
}
