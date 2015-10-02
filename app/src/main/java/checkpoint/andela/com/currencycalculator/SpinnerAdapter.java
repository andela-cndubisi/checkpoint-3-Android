package checkpoint.andela.com.currencycalculator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by andela-cj on 9/29/15.
 */
public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private String [] currency;
    private String [] currencyNames;

    public SpinnerAdapter(Context context, String[] object, String[] obj) {
        super(context, R.layout.simple_row, object);
        this.context = context;
        currency = object;
        currencyNames = obj;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(context);
        convertView = inflate.inflate(R.layout.simple_row, null);
        TextView currencyDescriptor = (TextView)convertView.findViewById(R.id.currency);
        currencyDescriptor.setText(currency[position]);
        return currencyDescriptor;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(context);
        convertView = inflate.inflate(R.layout.simple_row, null);
        TextView currencytext = (TextView)convertView.findViewById(R.id.currency);
        TextView currencyDescriptor = (TextView)convertView.findViewById(R.id.currencyDescriptor);
        currencytext.setText(currency[position]);
        currencyDescriptor.setText(currencyNames[position]);
        return convertView;

    }
}
