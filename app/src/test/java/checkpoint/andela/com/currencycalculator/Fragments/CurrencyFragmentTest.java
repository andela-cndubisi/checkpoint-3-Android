package checkpoint.andela.com.currencycalculator.Fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import java.awt.font.TextAttribute;
import java.text.NumberFormat;
import java.text.ParseException;

import checkpoint.andela.com.currencycalculator.BuildConfig;
import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.R;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by andela-cj on 9/27/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class CurrencyFragmentTest {
    private MainActivity activity;
    private CurrencyFragment wheel;
    private DisplayFragment display;
    @Before
    public void setUp() throws Exception {
        //    Given I am a User
        //    When I open the calculator app
        activity = Robolectric.buildActivity(MainActivity.class).create().start().get();
        wheel = (CurrencyFragment)activity.getFragmentManager().findFragmentById(R.id.currencylist);
        display = (DisplayFragment)activity.getFragmentManager().findFragmentById(R.id.screen);

    }

    /*
      As a user
      I need to be able calculate
      with multiple currencies
     */
    @Test
    public void testCurrencyClick(){
        //  Given I am a User
        //  Then I should be able to see a list of currencies
        assertTrue(wheel.isInLayout());
        assertNotNull(wheel);
        //  When I tap on an item in the list
        TextView currency = new TextView(activity);
        currency.setText("KWD");
        wheel.myList.performItemClick(currency, 0, 1);
        //  Then the displays' currency should update
        assertEquals("KWD", display.getCurrency());
    }
    /*
      As a user
      I need to be able calculate
      with multiple currencies
      And resulting currency
     */
    @Test
    public void testCurrencyLongClick(){
        //  Given I am a User
        //  Then I should be able to see a list of top 10 currencies
        assertTrue(wheel.isInLayout());
        assertNotNull(wheel);
        assertEquals(11, wheel.myList.getAdapter().getCount());
        //  When I long press on an item in the list
        final String text = "BHD";
        TextView currency = new TextView(activity);
        currency.setText(text);
        wheel.myList.getOnItemLongClickListener().onItemLongClick(null, currency, 1, 0);
        //  Then the display's currency should update
        assertEquals(text, display.getCurrency());
        //  And Base currency should be set to tapped currency
        assertEquals("BHD",wheel.converter.getBaseCurrency());
        assertEquals("Resulting currency: "+text,ShadowToast.getTextOfLatestToast());
    }

    /*
      As a user
      I need to be able calculate
      with multiple currencies
      And change the resulting currency and amount
     */
    @Test
    public void testChangeResultCurrencyOnLongClick() throws ParseException{
        //  Given I am a User
        //  Then I should be able to see a list of top 10 currencies
        assertTrue(wheel.isInLayout());
        assertNotNull(wheel);
        //  When I input '85'
        activity.display.update("85");
        //  Then I long press on an item in the list
        TextView currency = new TextView(activity);
        final String text = "CAD";
        currency.setText(text);
        wheel.myList.getOnItemLongClickListener().onItemLongClick(null,currency,0,0);
        //  Then the display's currency should update
        assertEquals(text, display.getCurrency());
        assertEquals(text,wheel.converter.getBaseCurrency());
        //  When the display currency changes
        //  Then the resulting amount should be update to the currency
        assertEquals(114, Math.round(NumberFormat.getInstance().parse(activity.getDisplayText()).doubleValue()));
        assertEquals("Resulting currency: "+text,ShadowToast.getTextOfLatestToast());
    }
}
