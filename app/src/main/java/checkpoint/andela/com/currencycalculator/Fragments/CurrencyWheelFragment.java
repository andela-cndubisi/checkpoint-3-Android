package checkpoint.andela.com.currencycalculator.Fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;

import checkpoint.andela.com.currencycalculator.Brain.CurrencyCalculator;
import checkpoint.andela.com.currencycalculator.FetchCurrencyRatesTask;
import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.R;

/**
 * Created by andela-cj on 9/22/15.
 */
public class CurrencyWheelFragment extends ListFragment {

    CurrencyCalculator calculator;
    String wCurrency;
    ListView myList;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                R.layout.simple_row, getResources().getStringArray(R.array.currency));
        FetchCurrencyRatesTask task = new FetchCurrencyRatesTask(getResources().getStringArray(R.array.currency));
        task.execute();
        setListAdapter(adapter);
        activity = (MainActivity)getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        myList = getListView();
        myList.setOnItemLongClickListener(onItemLongClickListener);
        calculator = activity.getBrain();
        wCurrency = calculator.getBaseCurrency();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        wCurrency = ( (TextView) v).getText().toString();
        calculator.setTmpCurrency(wCurrency);
        activity.setDisplayCurrency(wCurrency);
    }

     OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            String currency =  ( (TextView) view).getText().toString();
            NumberFormat numberFormat = NumberFormat.getNumberInstance();

            try {
                double amount = numberFormat.parse(activity.getDisplayText()).doubleValue();
                calculator.setBaseCurrency(currency);
                double newAmount = calculator.convert(amount);
                activity.setDisplayCurrency(currency);
                activity.updateDisplay(numberFormat.format(newAmount));
            } catch (ParseException e) {
                return false;
            }

            return true;
        }
    };
}
