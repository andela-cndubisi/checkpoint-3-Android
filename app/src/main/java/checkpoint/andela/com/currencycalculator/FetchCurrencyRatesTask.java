package checkpoint.andela.com.currencycalculator;

import android.os.AsyncTask;

import org.json.JSONException;

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

/**
 * Created by andela-cj on 9/22/15.
 */
public class FetchCurrencyRatesTask extends AsyncTask<String, Void , String> {
    CurrencyDataParser dataParser;

    String BaseURL = "https://query.yahooapis.com/v1/public/yql?q=";
    String query = "select*from yahoo.finance.xchange where pair in (";
    String param = "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    public FetchCurrencyRatesTask(String [] a){
        dataParser = new CurrencyDataParser();
        generateQuery(a);
    }

    private void generateQuery(String []a){

        String Base = "USD";
        for (int i = 0; i< a.length; i++){
            query += "\""+Base+ a[i]+"\"";
            if(i != a.length-1 ){
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
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String currencyRate= null;

        try {
            String ur1 = BaseURL+query+param;
            URL url = new URL(BaseURL+query+param);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream stream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (stream == null){  return null; }

            reader = new BufferedReader(new InputStreamReader(stream));
            String line;

            while ((line = reader.readLine()) != null){ buffer.append(line +"\n");  }

            if (buffer.length()== 0){  return null; }

            currencyRate = buffer.toString();
            return currencyRate;

        } catch (MalformedURLException e) {
            return null;
//                e.printStackTrace();
        } catch (ProtocolException e) {
//                return null;
            e.printStackTrace();
        } catch (IOException e) {
            return null;
//                e.printStackTrace();
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
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            dataParser.parse(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}