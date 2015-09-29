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
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;

import checkpoint.andela.com.currencycalculator.Brain.CurrencyConverter;
import checkpoint.andela.com.currencycalculator.TaskCurrencyRates;
import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.R;

/**
 * Created by andela-cj on 9/22/15.
 */
public class CurrencyFragment extends ListFragment {

    CurrencyConverter converter;
    String currency;
    ListView myList;
    MainActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                R.layout.simple_row, getResources().getStringArray(R.array.currency));
        TaskCurrencyRates task = new TaskCurrencyRates(getResources().getStringArray(R.array.currency));
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
        converter = activity.getBrain();
        currency = converter.getBaseCurrency();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        currency = ( (TextView) v).getText().toString();
        converter.setTempCurrency(currency);
        activity.setDisplayCurrency(currency);
        displayFeedBack("Computing currency: " + currency);

    }

     OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            String currency =  ( (TextView) view).getText().toString();
            NumberFormat numberFormat = NumberFormat.getNumberInstance();

            try {
                double amount = numberFormat.parse(activity.getDisplayText()).doubleValue();
                converter.setBaseCurrency(currency);
                double newAmount = converter.convert(amount);
                activity.setDisplayCurrency(currency);
                activity.updateDisplay(numberFormat.format(newAmount));
                converter.setTempCurrency(currency);
                displayFeedBack("Resulting currency: " + currency);
            } catch (ParseException e) {
                return false;
            }

            return true;
        }
    };

    public void displayFeedBack(String text){
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();

    }
}
