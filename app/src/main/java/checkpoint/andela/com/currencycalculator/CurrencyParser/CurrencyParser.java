package checkpoint.andela.com.currencycalculator.CurrencyParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by andela-cj on 9/22/15.
 */

public class CurrencyParser {
    public final static String baseCurrency = "USD";
    private static ArrayList<Currency> rates ;
    private JSONArray currentRates;
    String json;

    public CurrencyParser(String json){
        this.json = json;
        rates = new ArrayList<>();
    }

    public void parse()  {
        if (json != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
                JSONObject query = jsonObject.getJSONObject("query");
                JSONObject result;
                if((result = query.getJSONObject("results")) != null) {
                    currentRates = result.getJSONArray("rate");
                    generateRates();
                }else {
                    generateDefaultRates();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            generateDefaultRates();
        }
    }

    private void generateRates() throws JSONException {
        for(int i = 0; i < currentRates.length(); i++){

            JSONObject temp = currentRates.getJSONObject(i);
            rates.add(new Currency(cleanName(temp.getString("Name")),
                    temp.getDouble("Rate")));
        }
    }

    private String cleanName(String name){
        String base = baseCurrency +"/";
        name = name.substring(base.length());
        return name;
    }

    private void generateDefaultRates(){
            for (ExchangeRates xrate: ExchangeRates.values() ) {
                rates.add(new Currency(xrate.toString(), xrate.rate));
            }
    }

    public static Currency getCurrencyRate(String cur){
        for (Currency cr : rates){
            if (cr.getCurrency().equals(cur))
                return cr;
        }
        return null;
    }
}
