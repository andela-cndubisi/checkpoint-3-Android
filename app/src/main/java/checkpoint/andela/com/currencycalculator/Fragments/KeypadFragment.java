package checkpoint.andela.com.currencycalculator.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import checkpoint.andela.com.currencycalculator.CurrencyParser.CurrencyParser;
import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.Model.CurrencyConverter;
import checkpoint.andela.com.currencycalculator.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class KeypadFragment extends Fragment {
    private CurrencyConverter converter;
    private MainActivity activity;
    private DisplayDelegate delegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.keypadfragment, container, false);
        activity = (MainActivity) getActivity();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        converter = activity.converter;
    }

    public void digitPressed(View v){
        if (v instanceof Button) {
            converter.calculator.addDigit(((Button) v).getText().toString());
            delegate.update(converter.calculator.getResult());
        }
    }

    public void enterPressed(View v){
        if(v instanceof Button) {
            if(v.getId() == R.id.btnE) {
                converter.calculator.evaluate();
                delegate.update(converter.calculator.getResult());
                delegate.updateWithOperation(delegate.getMemoryText() + getFormattedResult() + " =");
            }
        }
    }

    public void clearPressed(View v){
        if(v instanceof Button) {
            if (v.getId() == R.id.btnC)
            converter.calculator.clear();
            converter.setBaseCurrency(CurrencyParser.baseCurrency);
            delegate.update(converter.calculator.getResult());
            delegate.updateWithOperation("");
        }
    }

    public void operationPressed(View v){
        if(v instanceof Button){
            String operation = ((Button)v).getText().toString();
            converter.calculator.processOperandStack();
            converter.calculator.currentOperation  = converter.calculator.getOperation(operation);
            converter.updateOldAmount();
            delegate.update(converter.calculator.getResult());
            delegate.updateWithOperation(getFormattedResult() + " "+ operation + " ");
        }
    }

    public void periodPressed(View v){
        converter.calculator.addDecimalPoint();
        delegate.update(converter.calculator.getResult());
    }

    public String getFormattedResult(){
        return converter.getTempCurrency() +" "+converter.oldAmount;
    }

    public void negatePressed(View v){
        if(v instanceof Button) {
            converter.calculator.negateDigit();
            delegate.update(converter.calculator.getResult());
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
        void updateWithOperation(String operation);
        String getDisplayText();
        String getMemoryText();
    }
}
