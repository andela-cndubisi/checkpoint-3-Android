package checkpoint.andela.com.currencycalculator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import checkpoint.andela.com.currencycalculator.KeypadFragment.DisplayDelegate;
import android.widget.TextView;

/**
 * Created by andela-cj on 9/21/15.
 */
public class DisplayFragment extends Fragment implements DisplayDelegate {
    private TextView display;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.displayfragment, container, false);
        display = (TextView)v.findViewById(R.id.display);

        return v;
    }

    @Override
    public void update(CharSequence result) {
        display.setText(result.toString());
    }
}
