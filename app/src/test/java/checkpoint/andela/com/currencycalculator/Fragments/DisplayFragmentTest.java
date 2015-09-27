package checkpoint.andela.com.currencycalculator.Fragments;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import checkpoint.andela.com.currencycalculator.BuildConfig;
import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.R;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by andela-cj on 9/27/15.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DisplayFragmentTest {
    private MainActivity activity;
    private DisplayFragment display;
    @Before
    public void setUp() throws Exception {
        //    Given I am a User
        //    When I open the calculator app
        activity = Robolectric.buildActivity(MainActivity.class).create().start().get();
        display = (DisplayFragment)activity.getFragmentManager().findFragmentById(R.id.screen);
    }

    @Test
    public void testCurrency(){
        //  Given I am a User
        //  Then I should be able to see a screen
        assertTrue(display.isInLayout());
        assertNotNull(display);
        //  And an initail currency of 'USD'
        assertEquals("USD",display.getCurrency());
    }

    @Test
    public void testDisplayText(){
        //  Given I am a User
        //  Then I should be able to see a screen
        assertTrue(display.isInLayout());
        assertNotNull(display);
        //  And an initial value of '0'
        assertEquals("0", display.getDisplayText());
    }

    @Test
    public void testUpadate(){
        //  Given I am a User
        //  Then I should be able to see a screen
        assertTrue(display.isInLayout());
        assertNotNull(display);
        //  And an initial value of '0'
        assertEquals("0", display.getDisplayText());
        //  When I tap '2' on the keypad
        display.update("2");
        //  Then the display should contain '2'
        assertEquals("2",display.getDisplayText());
    }
}
