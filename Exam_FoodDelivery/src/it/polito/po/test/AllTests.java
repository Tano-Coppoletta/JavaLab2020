package it.polito.po.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestR1_Delivery.class);
        suite.addTestSuite(TestR2_Orders.class);
        suite.addTestSuite(TestR3_Preparazione.class);
        suite.addTestSuite(TestR4_Stats.class);
        //$JUnit-END$
        return suite;
    }

}
