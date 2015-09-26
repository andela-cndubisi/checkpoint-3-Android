package checkpoint.andela.com.currencycalculator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by andela-cj on 9/22/15.
 */
public class CurrencyDataParser {
    private static ArrayList<CurrencyRates> rates ;
    private JSONArray currentRates;

    public CurrencyDataParser(){
        rates = new ArrayList<>();
    }

    public void parse(String json) throws JSONException {
        if (json != null) {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject query = jsonObject.getJSONObject("query");
            JSONObject result;
            if((result = query.getJSONObject("results")) != null) {
                currentRates = result.getJSONArray("rate");
                generateRates();
            }else {
                generateDefaultRates();
            }
        }else {
            generateDefaultRates();
        }
    }

    private void generateRates() throws JSONException {
        for(int i = 0; i < currentRates.length(); i++){
            rates.add(new CurrencyRates(cleanName(currentRates.getJSONObject(i).getString("Name")),
                    currentRates.getJSONObject(i).getDouble("Rate")));
        }
    }

    private String cleanName(String name){
        String base = "USD/";
        name = name.substring(base.length());
        return name;
    }

    private void generateDefaultRates(){
            for (XchangeRates xrate: XchangeRates.values() ) {
                rates.add(new CurrencyRates(xrate.toString(), xrate.rate));
            }
    }

    public static CurrencyRates getCurrencyRate(String cur){
        for (CurrencyRates crat : rates){
            if (crat.getCurrency().equals(cur))
                return crat;
        }
        return null;
    }
    public class CurrencyRates{
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

    public enum XchangeRates {
         USD (1.0)
        ,KWD (3.30)
        ,BHD (2.65)
        ,OMR (2.59)
        ,GBP (0.66)
        ,JOD (1.41)
        ,KYD (1.21)
        ,EUR (1.07)
        ,CHF (1.03)
        ,AZN (0.95)
        ,CAD (0.81);

        double rate;

        XchangeRates(double x) {
            rate = x;
        }
    }

}
