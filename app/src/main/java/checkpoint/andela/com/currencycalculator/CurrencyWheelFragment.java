package checkpoint.andela.com.currencycalculator;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by andela-cj on 9/22/15.
 */
public class CurrencyWheelFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Creating an array adapter to store the list of countries **/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.currency));
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
