package checkpoint.andela.com.currencycalculator.Fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import checkpoint.andela.com.currencycalculator.MainActivity;
import checkpoint.andela.com.currencycalculator.Model.CurrencyConverter;
import checkpoint.andela.com.currencycalculator.R;
import checkpoint.andela.com.currencycalculator.TaskCurrencyRates;

/**
 * Created by andela-cj on 9/22/15.
 */
public class CurrencyFragment extends ListFragment {

    CurrencyConverter converter;
    String wCurrency;
    ListView myList;
    MainActivity activity;
    int oldIndex = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                R.layout.list_row,getResources().getStringArray(R.array.currency));
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
        converter = activity.converter;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextView textView = (TextView)v;
        wCurrency = textView.getText().toString();
        converter.setBaseCurrency(BaseCurrencyFragment.getBaseCurrency());
        converter.setTempCurrency(wCurrency);
        if (oldIndex != -1)
            l.getChildAt(oldIndex).setBackgroundResource(R.color.default_color);
        v.setBackgroundResource(R.color.pressed_color);
        oldIndex = position;
    }
}