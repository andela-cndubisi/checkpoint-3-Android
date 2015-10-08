package checkpoint.andela.com.currencycalculator.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;

import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.Model.CurrencyConverter;
import checkpoint.andela.com.currencycalculator.R;
import checkpoint.andela.com.currencycalculator.SpinnerAdapter;

/**
 * Created by andela-cj on 08/10/2015.
 */
public class BaseCurrencyFragment extends Fragment {
    private Spinner baseCurrencySwitch;
    private TextView baseDescription;
    private static String baseCurrency;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.base_currency_fragment, container, false);
        baseCurrencySwitch = (Spinner) v.findViewById(R.id.spinnerCurrency);
        baseDescription = (TextView) v.findViewById(R.id.basecurrencytext);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        String [] a = getResources().getStringArray(R.array.currency);
        String[] currencyNames = getResources().getStringArray(R.array.currencynames) ;

        ArrayAdapter<String> b = new SpinnerAdapter(getActivity(),a, currencyNames);

        baseCurrencySwitch.setAdapter(b);
        baseDescription.setOnClickListener(onClickListener);
        baseCurrencySwitch.setOnItemSelectedListener(onItemSelectedListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            baseCurrencySwitch.performClick();
        }
    };

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            CurrencyConverter converter = ((MainActivity)getActivity()).getConverter();
            DisplayFragment display = ((MainActivity)getActivity()).getDisplay();
            String currency = (String) ((TextView)view).getText();

            double amount = 0;
            try {
                amount = NumberFormat.getInstance().parse(display.getDisplayText()).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            converter.setTempCurrency(converter.getBaseCurrency());
            baseCurrency = currency;
            converter.setBaseCurrency(baseCurrency);
            double newAmount = converter.convert(amount);
            display.update(NumberFormat.getInstance().format(newAmount));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public static String getBaseCurrency(){return  baseCurrency;}
    public Spinner getBaseCurrencySwitch() {return baseCurrencySwitch;}

}
