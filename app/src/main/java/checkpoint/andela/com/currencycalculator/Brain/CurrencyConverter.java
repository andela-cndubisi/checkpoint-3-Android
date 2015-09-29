package checkpoint.andela.com.currencycalculator.Brain;

import java.text.NumberFormat;
import java.text.ParseException;

import checkpoint.andela.com.currencycalculator.CurrencyParser.Currency;
import checkpoint.andela.com.currencycalculator.CurrencyParser.CurrencyDataParser;

/**
 * Created by andela-cj on 9/23/15.
 */
public class CurrencyConverter extends Calculator {

    public CurrencyConverter(){
        super();
    }

    String baseCurrency = "USD";

    String tempCurrency = baseCurrency;

    public void setTempCurrency(String tempCurrency) {
        Currency cr = CurrencyDataParser.getCurrencyRate(tempCurrency);
        this.tempCurrency = cr.getCurrency();
    }


    public double convert(double amount){
        if (!baseCurrency.equals(tempCurrency)){
         double currentInBase = convertToBase(amount);
         Currency cr = CurrencyDataParser.getCurrencyRate(tempCurrency);
           return currentInBase/ cr.getRate();
        }

        return amount;
    }

    private double convertToBase(double amount) {
        Currency cr = CurrencyDataParser.getCurrencyRate(baseCurrency);
        return amount*cr.getRate();
    }

    public void setBaseCurrency(String baseCurrency) {
        Currency cr = CurrencyDataParser.getCurrencyRate(baseCurrency);
        this.baseCurrency = cr.getCurrency();;
    }

    public String getBaseCurrency(){
        return baseCurrency;
    }


    @Override
    public void toggleIsTyping() {
        isTyping = false;
        try {
            double n = convert(Double.parseDouble(temp));
            String newtmp = NumberFormat.getInstance().format(n);
            operand.add(NumberFormat.getInstance().parse(newtmp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(operand.size() > 1){
            super.evaluate(currentOperation);
            tempCurrency = baseCurrency;
        }
    }


}
