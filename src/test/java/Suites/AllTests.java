package Suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JunitTestSuite.class, TestJunit1.class, TestJunit2.class })
public class AllTests {

}
