package checkpoint.andela.com.currencycalculator.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import checkpoint.andela.com.currencycalculator.R;

import android.widget.TextView;

/**
 * Created by andela-cj on 9/21/15.
 */
public class DisplayFragment extends Fragment implements KeypadFragment.DisplayDelegate {
    private TextView display;
    private TextView currency;
    private TextView memory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.displayfragment, container, false);
        display = (TextView)v.findViewById(R.id.display);
        currency = (TextView)v.findViewById(R.id.currency);
        memory = (TextView) v.findViewById(R.id.displayMemory);

        return v;
    }

    public void setCurrency(String cur){
        currency.setText(cur);
    }
    public void update(String result) {
        display.setText(result);
    }
    public String getDisplayText(){ return display.getText().toString(); }
    public String getCurrency(){ return currency.getText().toString(); }

}
