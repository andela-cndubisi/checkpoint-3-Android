package checkpoint.andela.com.currencycalculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import checkpoint.andela.com.currencycalculator.Model.Calculator;

import static org.junit.Assert.assertEquals;

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
    @Test
    public void testAdd(){
        calc.addDigit("2.5");
        calc.currentOperation = calc.getOperation("+");
        calc.evaluate();
        calc.addDigit("5.0");
        calc.evaluate();
        assertEquals("7.5",calc.getResult());
    }
    @Test
    public void testSubtraction(){

    }
    @Test
    public void testAddition(){

    }
    @Test
    public void testMultiplication(){

    }
    @Test
    public void testEvaluate(){

    }



}
