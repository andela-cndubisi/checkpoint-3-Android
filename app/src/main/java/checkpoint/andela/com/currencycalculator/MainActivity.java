package checkpoint.andela.com.currencycalculator;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
    private CurrencyCalculator brain;
    DisplayFragment display;
    KeypadFragment keypad;
    CurrencyWheelFragment currencyWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brain = new CurrencyCalculator();
        setUpFragment();
    }


    private void setUpFragment(){
        FragmentManager fm = getFragmentManager();
        display = (DisplayFragment)fm.findFragmentById(R.id.display);
        keypad = (KeypadFragment)fm.findFragmentById(R.id.keypad);
        currencyWheel = (CurrencyWheelFragment)fm.findFragmentById(R.id.currencyList);
        keypad.setDisplayDelegate(display);
//        if (display == null && keypad==null){
//            display = new DisplayFragment();
//            keypad = new KeypadFragment();
//            keypad.setDisplayDelegate(display);
//            fm.beginTransaction().add(R.id.container, display).add(R.id.container, keypad).commit();
//        }
    }



    public void enterPressed(View v){
        keypad.enterPressed(v);
        display.currency.setText(brain.baseCurrency.toString());
    }

    public void digitPressed(View v){
        keypad.digitPressed(((Button) v).getText().toString());
    }

    public void clearPressed(View v){
        keypad.clearPressed(v);
    }

    public void operationPressed(View v){ keypad.operationPressed(v); }

    public void periodPressed(View v){

    }
    public void negatePressed(View v){

    }

    public CurrencyCalculator getBrain() {
        return brain;
    }
}
