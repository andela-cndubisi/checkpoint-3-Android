package checkpoint.andela.com.currencycalculator;

/**
 * Created by andela-cj on 9/26/15.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import  static org.junit.Assert.*;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import checkpoint.andela.com.currencycalculator.Fragments.CurrencyFragment;
import checkpoint.andela.com.currencycalculator.Fragments.DisplayFragment;
import checkpoint.andela.com.currencycalculator.Fragments.KeypadFragment;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ActivityTest {
    MainActivity activity;
    @Before
    public void setUp() throws Exception {
        //    Given I am a User
        //    When I open the calculator app
        activity = Robolectric.buildActivity(MainActivity.class).create().start().get();
    }

    /*
    As a user
    I need to see numbered buttons
    so that I can do numeric computations
    */
    @Test
    public void testKeypad(){
        //  Then I should see numbered buttons
        //  And basic calculator operations
        KeypadFragment keypad = (KeypadFragment)activity.getFragmentManager().findFragmentById(R.id.keypad);
        assertTrue(keypad.isAdded());
        assertNotNull(keypad);
    }

    /*
    As a user
    I need see a screen
    So that I can see my computation results
    */
    @Test
    public void testScreen(){
        DisplayFragment screen = (DisplayFragment)activity.getFragmentManager().findFragmentById(R.id.screen);
        //  Then I should see a space for computation results
        assertTrue(screen.isAdded());
        assertNotNull(screen);
        //  And space should contains initial value of '0'
        assertEquals("0", screen.getDisplayText());
    }

    /*
    As a user
    I need see a screen
    So that I can see my computation results
    */
    @Test
    public void testCurrencyList(){
        CurrencyFragment wheel = (CurrencyFragment)activity.getFragmentManager().findFragmentById(R.id.currency_wheel);
        //  Then I should see list of currency option
        assertTrue(wheel.isAdded());
        assertNotNull(wheel);
    }
}