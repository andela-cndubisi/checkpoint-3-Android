package checkpoint.andela.com.currencycalculator;

/**
 * Created by andela-cj on 9/26/15.
 */
import android.widget.Button;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import  static org.junit.Assert.*;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import checkpoint.andela.com.currencycalculator.Fragments.KeypadFragment.DisplayDelegate;
import checkpoint.andela.com.currencycalculator.Fragments.KeypadFragment;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class KeypadFragmentTest {
    private MainActivity activity;
    private DisplayDelegate display;

    @Before
    public void setUp() throws Exception {
        //    Given I am a User
        //    When I see the calculator buttons and screen
        activity = Robolectric.buildActivity(MainActivity.class).create().start().get();
        KeypadFragment keypad = (KeypadFragment)activity.getFragmentManager().findFragmentById(R.id.keypad);
        display = keypad.getDisplayDelegate();
    }

   /*
     As a user
     I need to be able tap on numbered button
     And see the resulting number in the calculator's result screen
    */
    @Test
    public void testDigitButtons(){
        //    Given I am a User
        //    When I see the calculator buttons and screen
        Button btn0 = (Button)activity.findViewById(R.id.btn0);
        Button btn1 = (Button)activity.findViewById(R.id.btn1);
        Button btn2 = (Button)activity.findViewById(R.id.btn2);
        Button btn3 = (Button)activity.findViewById(R.id.btn3);
        Button btn4 = (Button)activity.findViewById(R.id.btn4);
        Button btn5 = (Button)activity.findViewById(R.id.btn5);
        Button btn6 = (Button)activity.findViewById(R.id.btn6);
        Button btn7 = (Button)activity.findViewById(R.id.btn7);
        Button btn8 = (Button)activity.findViewById(R.id.btn8);
        Button btn9 = (Button)activity.findViewById(R.id.btn9);
        //    And I tap on buttons '1','2','3','4','5','6','7','8','9','0'
        btn1.performClick();
        btn2.performClick();
        btn3.performClick();
        btn4.performClick();
        btn5.performClick();
        btn6.performClick();
        btn7.performClick();
        btn8.performClick();
        btn9.performClick();
        btn0.performClick();
        //    Then I should see '1234567890' in the calculator screen
        assertEquals("1234567890", display.getDisplayText());
    }

    /*
     As a user
     I need to be able to a do basic calculations like addition
     And see the see the result
    */
    @Test
    public void testAddition(){
        //    Given I am a User
        //    When I see the calculator buttons and screen
        //    And I tap '2'
        Button btn2 = (Button)activity.findViewById(R.id.btn2);
        btn2.performClick();
        //    Then I see '2' in the display
        assertEquals("2", display.getDisplayText());
        //    And I tap add '+'
        Button btnAdd = (Button)activity.findViewById(R.id.btnAdd);
        btnAdd.performClick();
        //    And I tap '2'
        btn2.performClick();
        //    Then I see '2' in the dispaly
        assertEquals("2", display.getDisplayText());
        //    And I tap =
        Button btnEqual = (Button)activity.findViewById(R.id.btnE);
        btnEqual.performClick();
        //    Then I should see a result of 4 in the display
        assertEquals("Display has result","4", display.getDisplayText() );
    }

    /*
     As a user
     I need to be able to a do basic calculations like subtraction
     And see the see the result
    */
    @Test
    public void testSubtraction(){
        //    Given I am a User
        //    When I see the calculator buttons and screen
        //    And I tap 5
        Button btn5 = (Button)activity.findViewById(R.id.btn5);
        btn5.performClick();
        //    Then I see 5 in the display
        assertEquals("5", display.getDisplayText());
        //    And I tap add
        Button b = (Button)activity.findViewById(R.id.btnMinus);
        b.performClick();
        //    And I tap 2
        Button btn2 = (Button)activity.findViewById(R.id.btn2);
        btn2.performClick();
        //    Then I see 2 in the display
        assertEquals("2", display.getDisplayText());
        //    And I tap Equals (=)
        Button btnE = (Button)activity.findViewById(R.id.btnE);
        btnE.performClick();
        //    Then I should see a result of 3 in the display
        assertEquals("Display has result","3", display.getDisplayText());
    }

    /*
    As a user
    I need to be able to a do basic calculations like multiplication
    And see the see the result
    */
    @Test
    public void testMultiplication(){
        //    Given I am a User
        //    When I see the calculator buttons and screen
        //    And I tap 5
        Button btn5 = (Button)activity.findViewById(R.id.btn5);
        btn5.performClick();
        //    Then I see 5 in the display
        assertEquals("5", display.getDisplayText());
        //    And I tap multiply
        Button b = (Button)activity.findViewById(R.id.btnMultiply);
        b.performClick();
        //    And I tap 2
        Button btn2 = (Button)activity.findViewById(R.id.btn2);
        btn2.performClick();
        //    Then I see 2 in the display
        assertEquals("2", display.getDisplayText());
        //    And I tap Equals (=)
        Button btnE = (Button)activity.findViewById(R.id.btnE);
        btnE.performClick();
        //    Then I should see a result of 3 in the display
        assertEquals("Display has result","10", display.getDisplayText());
    }

    /*
    As a user
    I need to be able to a do basic calculations like division
    And see the see the result
    */
    @Test
    public void testDivision(){
        //    Given I am a User
        //    When I see the calculator buttons and screen
        //    And I tap '5'
        Button btn5 = (Button)activity.findViewById(R.id.btn5);
        btn5.performClick();
        //    Then I see '5' in the display
        assertEquals("5", display.getDisplayText());
        //    And I tap divide '÷'
        Button btndiv = (Button)activity.findViewById(R.id.btnDivide);
        btndiv.performClick();
        //    And I tap '2'
        Button btn2 = (Button)activity.findViewById(R.id.btn2);
        btn2.performClick();
        //    Then I see '2' in the display
        assertEquals("2", display.getDisplayText());
        //    And I tap Equals '='
        Button btnE = (Button)activity.findViewById(R.id.btnE);
        btnE.performClick();
        //    Then I should see a result of '3' in the display
        assertEquals("Display has result","2.5", display.getDisplayText());
    }

    /*
     As a user
     I need to be able to a do basic calculations like division
     And see the see the result
     Then clear my result
     So I can start again
     */
    @Test
    public void testClear(){
        //    Given I am a User
        //    When I see the calculator buttons and screen
        //    And I tap '5'
        Button btn5 = (Button)activity.findViewById(R.id.btn5);
        btn5.performClick();
        //    Then I see '5' in the display
        assertEquals("5", display.getDisplayText());
        //    And I tap divide '÷'
        Button btndiv = (Button)activity.findViewById(R.id.btnDivide);
        btndiv.performClick();
        //    And I tap '2'
        Button btn2 = (Button)activity.findViewById(R.id.btn2);
        btn2.performClick();
        //    Then I see '2' in the display
        assertEquals("2", display.getDisplayText());
        //    And I tap Equals '='
        Button btnE = (Button)activity.findViewById(R.id.btnE);
        btnE.performClick();
        //    Then I should see a result of '3' in the display
        assertEquals("2.5", display.getDisplayText());
        //    And I tap 'C'
        Button btnC = (Button)activity.findViewById(R.id.btnC);
        btnC.performClick();
        //    Then I should see '0' in the display
        assertEquals("Display has result", "0", display.getDisplayText());
    }

    /*
     As a user
     I need to be able use floating numbers
    */
    @Test
    public void testDecimal(){
        //    Given I am a User
        //    When I see the calculator buttons and screen
        Button btnp = (Button)activity.findViewById(R.id.btnp);
        Button btn5 = (Button)activity.findViewById(R.id.btn5);
        Button btn6 = (Button)activity.findViewById(R.id.btn6);
        Button btn7 = (Button)activity.findViewById(R.id.btn7);
        //    And I tap '5'
        btn5.performClick();
        //    And I tap '.'
        btnp.performClick();
        //    And I tap '6'
        btn6.performClick();
        //    And I tap '.'
        btnp.performClick();
        //    And I tap '7'
        btn7.performClick();
        //    Then I see 5.67
        assertEquals("5.67", display.getDisplayText());
    }
    /*
     As a user
     I need to be able change
     the sign of a  numbers
    */
    @Test
    public void testSignChange(){
        //    Given I am a User
        //    When I see the calculator buttons and screen
        Button btnp = (Button)activity.findViewById(R.id.btnp);
        Button btn5 = (Button)activity.findViewById(R.id.btn5);
        Button btn6 = (Button)activity.findViewById(R.id.btn6);
        Button btn7 = (Button)activity.findViewById(R.id.btn7);
        Button btn_ = (Button)activity.findViewById(R.id.btnNegate);
        //    And I tap '5'
        btn5.performClick();
        //    And I tap '.'
        btnp.performClick();
        //    And I tap '6'
        btn6.performClick();
        //    And I tap '-'
        btn_.performClick();
        //    Then I should see '-5.6' in the display
        assertEquals("-5.6", display.getDisplayText());
        //    And I tap '-' again
        btn_.performClick();
        //    Then I should see '5.6' in the display
        assertEquals("5.6", display.getDisplayText());
    }
}
