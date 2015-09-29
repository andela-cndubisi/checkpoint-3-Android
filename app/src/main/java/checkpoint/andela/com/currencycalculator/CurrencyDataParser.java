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
    String json;

    public CurrencyDataParser(String json){
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
        for (CurrencyRates cr : rates){
            if (cr.getCurrency().equals(cur))
                return cr;
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
        ,KWD (0.3021)
        ,BHD (0.3772)
        ,OMR (0.3851)
        ,GBP (0.6584)
        ,JOD (0.7089)
        ,KYD (0.8200)
        ,EUR (0.8942)
        ,CHF (0.9785)
        ,AZN (1.0465)
        ,CAD (1.3334);

        double rate;

        XchangeRates(double x) {
            rate = x;
        }
    }
}
