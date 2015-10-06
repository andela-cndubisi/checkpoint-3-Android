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
    private Spinner baseCurrencySwitch;
    private TextView memory;
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.displayfragment, container, false);
        display = (TextView)v.findViewById(R.id.display);
        memory = (TextView) v.findViewById(R.id.displayMemory);
        baseCurrencySwitch = (Spinner) v.findViewById(R.id.spinnerCurrency);
        activity = ((MainActivity)getActivity());
        TaskCurrencyRates task = new TaskCurrencyRates(getResources().getStringArray(R.array.currency));
        task.execute();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        String [] a = getResources().getStringArray(R.array.currency);
        String[] currencyNames = getResources().getStringArray(R.array.currencynames) ;

        ArrayAdapter<String> b = new SpinnerAdapter(activity,a, currencyNames);

        baseCurrencySwitch.setAdapter(b);
        baseCurrencySwitch.setOnItemSelectedListener(onItemSelectedListener);
    }

    public void update(String result) {
        display.setText(result);
    }
    public void updateWithOperation(String operation) { memory.setText(operation);}
    public String getDisplayText(){ return display.getText().toString(); }
    public Spinner getBaseCurrencySwitch() {return baseCurrencySwitch;}


    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            CurrencyConverter converter = ((MainActivity)getActivity()).converter;
            DisplayFragment display = ((MainActivity)getActivity()).display;
            String currency = (String) ((TextView)view).getText();

            double amount = 0;
            try {
                amount = NumberFormat.getInstance().parse(display.getDisplayText()).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String oldTemp = converter.getTempCurrency();
            converter.setTempCurrency(converter.getBaseCurrency());
            converter.setBaseCurrency(currency);
            double newAmount = converter.convert(amount);
            display.update(NumberFormat.getInstance().format(newAmount));
            converter.setTempCurrency(oldTemp);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}
