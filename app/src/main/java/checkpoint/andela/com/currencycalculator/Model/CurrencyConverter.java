package checkpoint.andela.com.currencycalculator.Model;

import java.text.NumberFormat;
import java.text.ParseException;

import checkpoint.andela.com.currencycalculator.CurrencyParser.Currency;
import checkpoint.andela.com.currencycalculator.CurrencyParser.CurrencyParser;

/**
 * Created by andela-cj on 9/23/15.
 */
public class CurrencyConverter {
    public Calculator calculator;

    public CurrencyConverter(){
        super();
        calculator = new Calculator();
    }

    private String baseCurrency = CurrencyParser.baseCurrency;

    private String tempCurrency = baseCurrency;

    public void setTempCurrency(String tempCurrency) {
        Currency cr = CurrencyParser.getCurrencyRate(tempCurrency);
        this.tempCurrency = cr.getCurrency();
    }


    public double convert(double amount){
        if (!baseCurrency.equals(tempCurrency)){
         double currentInBase = convertToBase(amount);
         Currency cr = CurrencyParser.getCurrencyRate(tempCurrency);
           return currentInBase/ cr.getRate();
        }

        return amount;
    }

    private double convertToBase(double amount) {
        Currency cr = CurrencyParser.getCurrencyRate(baseCurrency);
        return amount*cr.getRate();
    }

    public void setBaseCurrency(String baseCurrency) {
        Currency cr = CurrencyParser.getCurrencyRate(baseCurrency);
        this.baseCurrency = cr.getCurrency();;
    }

    public String getBaseCurrency(){
        return baseCurrency;
    }


    public void update() {
        double n = convert(Double.parseDouble(calculator.getResult()));
        String newtmp = NumberFormat.getInstance().format(n);
        calculator.setTemp(newtmp);
        calculator.toggleIsTyping();
        tempCurrency = baseCurrency;
    }


}
