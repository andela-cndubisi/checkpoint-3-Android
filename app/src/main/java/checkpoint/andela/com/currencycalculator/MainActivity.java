package checkpoint.andela.com.currencycalculator;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class MainActivity extends ActionBarActivity {
    DisplayFragment display;
    KeypadFragment keypad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpFragment();
    }

    private void setUpFragment(){
        FragmentManager fm = getFragmentManager();
        display = (DisplayFragment)fm.findFragmentById(R.id.display);
        keypad = (KeypadFragment)fm.findFragmentById(R.id.keypad);
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
    }

    public void digitPressed(View v){
        keypad.digitPressed(v);
    }

    public void clearPressed(View v){
        keypad.clearPressed(v);
    }

    public void operationPressed(View v){ keypad.operationPressed(v); }

    public void periodPressed(View v){

    }

    public void negatePressed(View v){

    }
}
