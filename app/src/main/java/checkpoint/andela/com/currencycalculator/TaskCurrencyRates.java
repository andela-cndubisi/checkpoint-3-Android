package checkpoint.andela.com.currencycalculator;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import checkpoint.andela.com.currencycalculator.CurrencyParser.CurrencyParser;

/**
 * Created by andela-cj on 9/22/15.
 */
public class TaskCurrencyRates extends AsyncTask<String, Void , String> {
    private String query = "select*from yahoo.finance.xchange where pair in (";

    public TaskCurrencyRates(String[] keys){
        generateQuery(keys);
        new CurrencyParser(null).parse();
    }

    private void generateQuery(String []keys){
        String Base = CurrencyParser.baseCurrency;

        for (int i = 0; i< keys.length; i++){
            query += "\""+ Base + keys[i]+"\"";
            if(i != keys.length-1 ){
                query += ",";
            }
        }
        query +=")";
        try {
            query = URLEncoder.encode(query, "US-ASCII");
        }  catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        final String BaseURL = "https://query.yahooapis.com/v1/public/yql?q=";
        final String param = "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String currencyRate;

        try {
            URL url = new URL(BaseURL+query+param);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream stream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();

            if (stream == null){  return null; }

            reader = new BufferedReader(new InputStreamReader(stream));
            String line;

            while ((line = reader.readLine()) != null){ buffer.append(line +"\n");  }

            if (buffer.length()== 0){  return null; }

            currencyRate = buffer.toString();
            return currencyRate;

        } catch (MalformedURLException e) {
            new CurrencyParser(null).parse();
            return null;
        } catch (ProtocolException e) {
            new CurrencyParser(null).parse();
            return null;
        } catch (IOException e) {
            new CurrencyParser(null).parse();
            return null;
        } finally {
            if (urlConnection !=null){
                urlConnection.disconnect();
            }
            if (reader !=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String s) {
        new CurrencyParser(s).parse();
    }
}