package checkpoint.andela.com.currencycalculator;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import checkpoint.andela.com.currencycalculator.Fragments.BaseCurrencyFragment;
import checkpoint.andela.com.currencycalculator.Fragments.CurrencyFragment;
import checkpoint.andela.com.currencycalculator.Model.CurrencyConverter;
import checkpoint.andela.com.currencycalculator.Fragments.DisplayFragment;
import checkpoint.andela.com.currencycalculator.Fragments.KeypadFragment;

public class MainActivity extends ActionBarActivity {
    public CurrencyConverter converter;
    public DisplayFragment display;
    private KeypadFragment keypad;
    private BaseCurrencyFragment baseCurrency;
    private CurrencyFragment wheel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        converter = new CurrencyConverter();
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        setUpFragment();
    }

    private void setUpFragment(){
        FragmentManager fm = getFragmentManager();
        display = (DisplayFragment)fm.findFragmentById(R.id.screen);
        keypad = (KeypadFragment)fm.findFragmentById(R.id.keypad);
        keypad.setDisplayDelegate(display);
        baseCurrency = (BaseCurrencyFragment)fm.findFragmentById(R.id.base_currency);
        wheel = (CurrencyFragment)fm.findFragmentById(R.id.currency_wheel);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public String getDisplayText(){
        return display.getDisplayText();
    }


    /* click listener */
    public void digitPressed(View v) { keypad.digitPressed(v); }
    public void clearPressed(View v) { keypad.clearPressed(v); }
    public void operationPressed(View v){ keypad.operationPressed(v); }
    public void periodPressed(View v) { keypad.periodPressed(v); }
    public void negatePressed(View v) { keypad.negatePressed(v);  }
    public void enterPressed(View v){ keypad.enterPressed(v); }
    public CurrencyConverter getConverter(){return converter;}
    public DisplayFragment getDisplay() {
        return display;
    }
}
