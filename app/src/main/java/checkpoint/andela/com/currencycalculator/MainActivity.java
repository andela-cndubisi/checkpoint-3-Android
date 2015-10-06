package checkpoint.andela.com.currencycalculator;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import checkpoint.andela.com.currencycalculator.Fragments.CurrencyFragment;
import checkpoint.andela.com.currencycalculator.Model.Calculator;
import checkpoint.andela.com.currencycalculator.Model.CurrencyConverter;
import checkpoint.andela.com.currencycalculator.Fragments.DisplayFragment;
import checkpoint.andela.com.currencycalculator.Fragments.KeypadFragment;

public class MainActivity extends ActionBarActivity {
    public CurrencyConverter converter;
    public DisplayFragment display;
    private KeypadFragment keypad;
    private CurrencyFragment wheel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        converter = new CurrencyConverter();
        setUpFragment();
    }

    private void setUpFragment(){
        FragmentManager fm = getFragmentManager();
        display = (DisplayFragment)fm.findFragmentById(R.id.screen);
        keypad = (KeypadFragment)fm.findFragmentById(R.id.keypad);
        keypad.setDisplayDelegate(display);
        wheel = (CurrencyFragment)fm.findFragmentById(R.id.currency_wheel);
    }

    @TargetApi(19)
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
    public Calculator getCalculator() { return converter.calculator; }
    public void enterPressed(View v){ keypad.enterPressed(v); }

}
