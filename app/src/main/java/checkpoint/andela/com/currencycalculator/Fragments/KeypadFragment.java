package checkpoint.andela.com.currencycalculator.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import checkpoint.andela.com.currencycalculator.Brain.Calculator;
import checkpoint.andela.com.currencycalculator.Brain.CurrencyConverter;
import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.R;

import static java.lang.Character.isDigit;

/**
 * A placeholder fragment containing a simple view.
 */
public class KeypadFragment extends Fragment {
    private Calculator brain;
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

    public void digitPressed(View v){
        if (v instanceof Button) {
            brain.addDigit(((Button) v).getText().toString());
            delegate.update(brain.getResult());
        }
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

    public void periodPressed(View v){
        brain.addDecimalPoint();
        delegate.update(brain.getResult());
    }

    public void negatePressed(View v){
        if(v instanceof Button) {
            brain.negateDigit();
            delegate.update(brain.getResult());
        }
    }

    public void setDisplayDelegate(DisplayDelegate delegate){
        this.delegate = delegate;
    }
    public DisplayDelegate getDisplayDelegate(){
        return delegate;
    }

    public interface DisplayDelegate{
        void update(String result);
        String getDisplayText();
    }
}
