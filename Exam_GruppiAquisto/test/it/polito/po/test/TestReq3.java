package it.polito.po.test;
import junit.framework.TestCase;
//import java.util.*;
import groups.*;

public class TestReq3 extends TestCase {
    GroupHandling gh;
    protected void setUp() throws Exception {
        gh = new GroupHandling();
		gh.addProduct("beta", "f1","f3","f2");
		gh.addProduct("alfa", "f2","f5","f3");
		gh.addProduct("gamma", "f6","f3","f4","f1");
		gh.addGroup("betaGroup", "beta", "q5","p3","p2");
		gh.addGroup("alfaGroup", "alfa", "q4","p3","p1");
		gh.addGroup("gammaGroup", "gamma", "p2","p3","p1");
		gh.setSuppliers("gammaGroup","f4","f3","f1");
	}
	
	public void testSetSuppliers() {
		try {
			gh.setSuppliers("betaGroup","f2","f3","f8");
			fail("unknown supplier");
		} catch (Exception e) {
			// OK
		}
	}
	
	public void testSetSuppliers2() {
		try {
			gh.setSuppliers("alfaGroup","f2","f6");
			fail("unsuitable supplier");
		} catch (Exception e) {
            // OK
		}
	}
	public void testAddBid() {
		try {
			gh.addBid("gammaGroup", "f5", 500);
			fail("supplier not in group"); //defined but not in group
		} catch (Exception e) {
            // OK
		}
	}
	
	public void testGetBids() throws GroupHandlingException {
			gh.addBid("gammaGroup", "f4", 400);
			gh.addBid("gammaGroup", "f3", 300);
			gh.addBid("gammaGroup", "f1", 400);
			String result = gh.getBids("gammaGroup");  
			//System.out.println(result); //f3:300,f1:400,f4:400
			String s = "f3:300,f1:400,f4:400";
			assertNotNull(result);
			assertEquals(s,result);
	}
	
	
}
