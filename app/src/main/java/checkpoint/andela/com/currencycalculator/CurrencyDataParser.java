package checkpoint.andela.com.currencycalculator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by andela-cj on 9/22/15.
 */
public class CurrencyDataParser {
    ArrayList<CurrencyRates> rates ;
    public JSONArray currentRates;

    public CurrencyDataParser(){
        rates = new ArrayList<>();
    }

    public void parse(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject query = jsonObject.getJSONObject("query").getJSONObject("results");
        currentRates = query.getJSONArray("rate");
        generateRates();
    }

    private void generateRates() throws JSONException {
        for(int i = 0; i < currentRates.length(); i++){
            rates.add(new CurrencyRates(currentRates.getJSONObject(i).getString("Name"),
                    currentRates.getJSONObject(i).getDouble("Rate")));
        }
    }

    private class CurrencyRates{
        private String currency;
        private double rate;

        public CurrencyRates(String currency, double rate){
            this.currency = currency;
            this.rate = rate;
        }

        public String getCurrency(){
            return currency;
        }
        public double getRate(){
            return rate;
        }
    }
}
