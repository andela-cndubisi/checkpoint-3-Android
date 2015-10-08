package checkpoint.andela.com.currencycalculator.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.Model.CurrencyConverter;
import checkpoint.andela.com.currencycalculator.R;
import checkpoint.andela.com.currencycalculator.SpinnerAdapter;
import checkpoint.andela.com.currencycalculator.TaskCurrencyRates;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;
/**
 * Created by andela-cj on 9/21/15.
 */
public class DisplayFragment extends Fragment implements KeypadFragment.DisplayDelegate {
    private TextView display;
    private TextView memory;
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.displayfragment, container, false);
        display = (TextView)v.findViewById(R.id.display);
        memory = (TextView) v.findViewById(R.id.displayMemory);
        activity = ((MainActivity)getActivity());
        TaskCurrencyRates task = new TaskCurrencyRates(getResources().getStringArray(R.array.currency));
        task.execute();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void update(String result) {
        display.setText(result);
    }
    public void updateWithOperation(String operation) { memory.setText(operation);}
    public String getDisplayText(){ return display.getText().toString(); }

}
