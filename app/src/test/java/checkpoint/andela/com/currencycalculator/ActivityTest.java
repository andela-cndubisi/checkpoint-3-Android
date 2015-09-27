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

import checkpoint.andela.com.currencycalculator.Fragments.CurrencyWheelFragment;
import checkpoint.andela.com.currencycalculator.Fragments.DisplayFragment;
import checkpoint.andela.com.currencycalculator.Fragments.KeypadFragment;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ActivityTest {
    MainActivity activity;
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().start().get();
    }

    @Test
    public void testKeypad(){
        KeypadFragment keypad = (KeypadFragment)activity.getFragmentManager().findFragmentById(R.id.keypad);
        assertTrue(keypad.isAdded());
        assertNotNull(keypad);
    }

    @Test
    public void testScreen(){
        DisplayFragment screen = (DisplayFragment)activity.getFragmentManager().findFragmentById(R.id.screen);
        assertTrue(screen.isAdded());
        assertNotNull(screen);
    }

    @Test
    public void testCurrencyList(){
        CurrencyWheelFragment wheel = (CurrencyWheelFragment)activity.getFragmentManager().findFragmentById(R.id.currencylist);
        assertTrue(wheel.isAdded());
        assertNotNull(wheel);
    }



}