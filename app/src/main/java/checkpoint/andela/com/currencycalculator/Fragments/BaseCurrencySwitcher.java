package checkpoint.andela.com.currencycalculator.Fragments;

import android.app.Activity;
import android.view.View;
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
public class BaseCurrencySwitcher {
    private static String baseCurrency;
    private Activity activity;
    public BaseCurrencySwitcher(Activity activity){
        this.activity = activity;
    }
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            CurrencyConverter converter = ((MainActivity)activity).getConverter();
            DisplayFragment display = ((MainActivity)activity).getDisplay();
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

    public void setupSpinner(Spinner spinner){
        String [] a = activity.getResources().getStringArray(R.array.currency);
        String[] currencyNames = activity.getResources().getStringArray(R.array.currencynames) ;
        SpinnerAdapter b = new SpinnerAdapter(activity,a, currencyNames);
        spinner.setAdapter(b);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
    }
}
