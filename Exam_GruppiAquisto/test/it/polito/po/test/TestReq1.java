package it.polito.po.test;
import junit.framework.TestCase;
import java.util.*;
import groups.*;

public class TestReq1 extends TestCase {
	GroupHandling gh;
	protected void setUp() throws Exception {
	    gh = new GroupHandling();
		gh.addProduct("beta", "f1","f3","f2");
		gh.addProduct("alfa", "f2","f5","f3");
		gh.addProduct("gamma", "f6","f3","f4","f1");
	}
	
	public void testAddProduct() {
		try {
			gh.addProduct("alfa", "f6");
			fail("product duplicated");
		} catch (Exception e) {
			//OK
		}
		}
		
	public void testGetProductTypes() {
			List<String> list = gh.getProductTypes("f3");  
			assertNotNull(list);
			String s = "[alfa, beta, gamma]";
			assertEquals(s,list.toString());
	}
	
	
}
