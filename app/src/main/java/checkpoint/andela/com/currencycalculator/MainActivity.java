package checkpoint.andela.com.currencycalculator;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import checkpoint.andela.com.currencycalculator.Brain.CurrencyCalculator;
import checkpoint.andela.com.currencycalculator.Fragments.CurrencyWheelFragment;
import checkpoint.andela.com.currencycalculator.Fragments.DisplayFragment;
import checkpoint.andela.com.currencycalculator.Fragments.KeypadFragment;

public class MainActivity extends ActionBarActivity {
    private CurrencyCalculator brain;
    public DisplayFragment display;
    private KeypadFragment keypad;
    private CurrencyWheelFragment currencyWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brain = new CurrencyCalculator();
        setUpFragment();
    }

    private void setUpFragment(){
        FragmentManager fm = getFragmentManager();
        display = (DisplayFragment)fm.findFragmentById(R.id.screen);
        keypad = (KeypadFragment)fm.findFragmentById(R.id.keypad);
        currencyWheel = (CurrencyWheelFragment)fm.findFragmentById(R.id.currencylist);
        keypad.setDisplayDelegate(display);
    }



    public void setDisplayCurrency(String curncy){
        display.setCurrency(curncy);
    }

    public String getDisplayText(){
        return display.getDisplayText();
    }

    public void updateDisplay(String str){
        display.update(str);
    }


    /* click listenser */
    public void digitPressed(View v) { keypad.digitPressed(v); }
    public void clearPressed(View v) { keypad.clearPressed(v); }
    public void operationPressed(View v){ keypad.operationPressed(v); }
    public void periodPressed(View v) { keypad.periodPressed(v); }
    public void negatePressed(View v) { keypad.negatePressed(v);  }
    public CurrencyCalculator getBrain() { return brain; }
    public void enterPressed(View v){
        keypad.enterPressed(v);
        display.setCurrency(brain.getBaseCurrency());
    }
}
