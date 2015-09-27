package checkpoint.andela.com.currencycalculator.Fragments;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import checkpoint.andela.com.currencycalculator.BuildConfig;
import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.R;

/**
 * Created by andela-cj on 9/27/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class CurrencyFragmentTest {
    private MainActivity activity;
    private CurrencyFragment wheel;
    @Before
    public void setUp() throws Exception {
        //    Given I am a User
        //    When I open the calculator app
        activity = Robolectric.buildActivity(MainActivity.class).create().start().get();
        wheel = (CurrencyFragment)activity.getFragmentManager().findFragmentById(R.id.currencylist);
    }


}
