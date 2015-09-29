package checkpoint.andela.com.currencycalculator;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import checkpoint.andela.com.currencycalculator.Brain.CurrencyConverter;
import checkpoint.andela.com.currencycalculator.Fragments.CurrencyFragment;
import checkpoint.andela.com.currencycalculator.Fragments.DisplayFragment;
import checkpoint.andela.com.currencycalculator.Fragments.KeypadFragment;

public class MainActivity extends ActionBarActivity {
    private CurrencyConverter brain;
    public DisplayFragment display;
    private KeypadFragment keypad;
    private CurrencyFragment currencyWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brain = new CurrencyConverter();
        setUpFragment();
    }

    private void setUpFragment(){
        FragmentManager fm = getFragmentManager();
        display = (DisplayFragment)fm.findFragmentById(R.id.screen);
        keypad = (KeypadFragment)fm.findFragmentById(R.id.keypad);
        currencyWheel = (CurrencyFragment)fm.findFragmentById(R.id.currencylist);
        keypad.setDisplayDelegate(display);
    }


    @TargetApi(19)
    @Override
    protected void onStart() {
        super.onStart();
        new ShowcaseView.Builder(this).setTarget(new ViewTarget(currencyWheel.getView().getId(),this))
                .setContentTitle(R.string.tip)
                .setContentText(R.string.instructions)
                .hideOnTouchOutside()
                .build();
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
    public CurrencyConverter getBrain() { return brain; }
    public void enterPressed(View v){
        keypad.enterPressed(v);
        display.setCurrency(brain.getBaseCurrency());
    }
}
