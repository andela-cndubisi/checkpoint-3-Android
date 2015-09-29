package checkpoint.andela.com.currencycalculator.CurrencyParser;

/**
 * Created by andela-cj on 9/29/15.
 */
public class Currency {
    private String currency;
    private double rate;

    public Currency(String currency, double rate){
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