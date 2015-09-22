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
    private CalculatorBrain brain;
    private DisplayDelegate delegate;
    public KeypadFragment() {
        brain = new CalculatorBrain();
    }

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


    public void digitPressed(View v){
        if(v instanceof Button){
            brain.addDigit(((Button) v).getText().toString());
            delegate.update(brain.getResult());
        }
    }

    public void enterPressed(View v){
        if(v instanceof Button) {
            if(v.getId() == R.id.btnE) {
                brain.enter();
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
        void update(CharSequence result);
    }

}
