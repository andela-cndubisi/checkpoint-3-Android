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
import checkpoint.andela.com.currencycalculator.Brain.FetchCurrencyRatesTask;
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
                R.layout.simple_row,getResources().getStringArray(R.array.currency));
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
        calculator = ((MainActivity)getActivity()).getBrain();
        wCurrency = calculator.getBaseCurrency();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextView textView = (TextView)v;
        wCurrency = textView.getText().toString();
        calculator.setTmpCurrency(wCurrency);
        activity.display.currency.setText(wCurrency);
    }

    OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            TextView currency =  (TextView)view;

            try {
                double amount = NumberFormat.getInstance().parse(activity.display.getDisplayText()).doubleValue();
                calculator.setBaseCurrency(currency.getText().toString());
                double newAmount = calculator.convert(amount);
                // cleanup demilter
                activity.display.currency.setText(currency.getText());
                activity.display.update(NumberFormat.getInstance().format(newAmount));
            } catch (ParseException e) {
                return false;
            }

            return true;
        }
    };
}
