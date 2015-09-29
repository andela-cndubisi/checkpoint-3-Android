package checkpoint.andela.com.currencycalculator;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import checkpoint.andela.com.currencycalculator.Brain.Calculator;

/**
 * Created by andela-cj on 9/29/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class CalculatorTest {
    private Calculator calc;
    @Before
    public void setUp() throws Exception {
        calc = new Calculator();

    }

    public void testAdd(){

    }
    public void testSubstraction(){

    }
    public void testAdditon(){

    }
    public void testMutliplication(){

    }
    public void testEvaluate(){

    }



}
