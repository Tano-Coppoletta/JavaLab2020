package it.polito.po.test;
import junit.framework.TestCase;
//import java.util.*;
import groups.*;

public class TestReq4 extends TestCase {
    GroupHandling gh;
    protected void setUp() throws Exception {
        gh = new GroupHandling();
		gh.addProduct("beta", "f1","f3","f2");
		gh.addProduct("alfa", "f2","f5","f3");
		gh.addProduct("gamma", "f6","f3","f4","f1");
		gh.addGroup("betaGroup", "beta", "q5","p3","p2");
		gh.addGroup("alfaGroup", "alfa", "q4","p3","p1");
		gh.addGroup("gammaGroup", "gamma", "p2","p3","p1","p4");
		gh.setSuppliers("gammaGroup","f4","f3","f1","f6");
		gh.addBid("gammaGroup", "f4", 400);
		gh.addBid("gammaGroup", "f3", 300);
		gh.addBid("gammaGroup", "f1", 400);
		gh.vote("gammaGroup", "p1", "f3");
		gh.vote("gammaGroup", "p2", "f1");
		gh.vote("gammaGroup", "p3", "f1");
		gh.vote("gammaGroup", "p4", "f3");
	}
	
	public void testVote() {
		try {
			gh.vote("gammaGroup","q5","f4");
			fail("vote by customer not in group");
		} catch (Exception e) {
            // OK
		}
	}
	
	public void testVote2() {
		try {
			gh.vote("gammaGroup","p2","f6");
			fail("vote for undefined bid");
		} catch (Exception e) {
            // OK
		}
	}

	public void testGetVotes() {
			String result = gh.getVotes("gammaGroup");  
			String s = "f1:2,f3:2";
			assertNotNull(result);
			assertEquals(s,result);
	}
	
	public void testGetWinningBid() {
			String result = gh.getWinningBid("gammaGroup");
			//System.out.println(result); //f3:2
			String s = "f3:2";
            assertNotNull(result);
            assertEquals(s,result);
	}
	
	
}
