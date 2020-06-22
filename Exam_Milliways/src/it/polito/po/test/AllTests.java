package it.polito.po.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestR1_Race.class);
        suite.addTestSuite(TestR2_Party.class);
        suite.addTestSuite(TestR3_Hall.class);
        suite.addTestSuite(TestR4_Restaurant.class);
        suite.addTestSuite(TestR5_Stats.class);
        //$JUnit-END$
        return suite;
    }

}
