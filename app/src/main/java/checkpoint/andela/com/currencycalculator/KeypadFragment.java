package checkpoint.andela.com.currencycalculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static java.lang.Character.isDigit;

/**
 * A placeholder fragment containing a simple view.
 */
public class KeypadFragment extends Fragment {
    private CurrencyCalculator brain;
    private DisplayDelegate delegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.keypadfragment, container, false);
        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        brain = ((MainActivity)getActivity()).getBrain();
    }

    public void digitPressed(String digit){
           brain.addDigit(digit);
           delegate.update(brain.getResult());
    }

    public void enterPressed(View v){
        if(v instanceof Button) {
            if(v.getId() == R.id.btnE) {
                brain.evaluate();
                delegate.update(brain.getResult());
            }
        }
    }

    public void clearPressed(View v){
        if(v instanceof Button) {
            if (v.getId() == R.id.btnC) brain.clear();
            delegate.update(brain.getResult());
        }
    }

    public void operationPressed(View v){
        if(v instanceof Button){
            brain.toggleIsTyping();
            delegate.update(brain.getResult());
            brain.currentOperation  = brain.getOperation(((Button)v).getText().toString());
        }
    }

    public void negatePressed(View v){

    }

    public void setDisplayDelegate(DisplayDelegate delegate){
        this.delegate = delegate;
    }

    public interface DisplayDelegate{
        void update(String result);
    }


//    private enum DigitKeyPad{
//        zero("0"), one("1"), two("2"), three("3"), four("4"), five("5"), six("6"), seven("7"), eight("8"), nine("9");
//        String num ;
//        DigitKeyPad(String num){
//          this.num = num;
//        }
//
//    }
}
