package checkpoint.andela.com.currencycalculator.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import checkpoint.andela.com.currencycalculator.CurrencyParser.CurrencyParser;
import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.R;
import checkpoint.andela.com.currencycalculator.SpinnerAdapter;
import checkpoint.andela.com.currencycalculator.TaskCurrencyRates;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by andela-cj on 9/21/15.
 */
public class DisplayFragment extends Fragment implements KeypadFragment.DisplayDelegate {
    private TextView display;
    private Spinner currency;
    private TextView basecurrency;
    private TextView memory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.displayfragment, container, false);
        display = (TextView)v.findViewById(R.id.display);
        memory = (TextView) v.findViewById(R.id.displayMemory);
        basecurrency = (TextView) v.findViewById(R.id.currencybase);
        currency  = (Spinner) v.findViewById(R.id.spinnerCurrency);
        basecurrency.setText(CurrencyParser.baseCurrency);
        TaskCurrencyRates task = new TaskCurrencyRates(getResources().getStringArray(R.array.currency));
        task.execute();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        String [] a = getResources().getStringArray(R.array.currency);
        String[] currencyNames = getResources().getStringArray(R.array.currencynames) ;

        ArrayAdapter<String> b = new SpinnerAdapter(getActivity(),a, currencyNames);
        currency.setAdapter(b);
        currency.setOnItemSelectedListener(onItemSelectedListener);

    }

    public void update(String result) {
        display.setText(result);
    }
    public String getDisplayText(){ return display.getText().toString(); }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            TextView currency = (TextView)parent.findViewById(R.id.currency);
            MainActivity activity = (MainActivity) getActivity();
            activity.converter.setTempCurrency(currency.getText().toString());
            activity.converter.update();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


}
