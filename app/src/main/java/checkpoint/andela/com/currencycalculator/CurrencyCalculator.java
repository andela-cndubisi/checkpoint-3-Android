package checkpoint.andela.com.currencycalculator;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by andela-cj on 9/23/15.
 */
public class CurrencyCalculator extends CalculatorBrain {

    public CurrencyCalculator(){
        super();
    }

    String baseCurrency = "USD";

    String tmpCurrency = baseCurrency;

    public void setTmpCurrency(String tmpCurrency) {
        CurrencyDataParser.CurrencyRates cr = CurrencyDataParser.getCurrencyRate(tmpCurrency);
        this.tmpCurrency = cr.getCurrency();
    }


    public double convert(String currentC, String newC, double amount){
        if (!currentC.equals(newC)){
        double currentInBase = convertToUSD(currentC, amount);
        CurrencyDataParser.CurrencyRates cr = CurrencyDataParser.getCurrencyRate(baseCurrency);
           return cr.getRate() * currentInBase;
        }

        return amount;
    }

    private double convertToUSD(String currentC, double amount) {

        CurrencyDataParser.CurrencyRates cr = CurrencyDataParser.getCurrencyRate(tmpCurrency);
        return amount/cr.getRate();
    }

    public void setBaseCurrency(String baseCurrency) {
        CurrencyDataParser.CurrencyRates cr = CurrencyDataParser.getCurrencyRate(tmpCurrency);
        this.baseCurrency = cr.getCurrency();;
    }

    public String getBaseCurrency(){
        return baseCurrency;
    }


    @Override
    public void toggleIsTyping() {
        isTyping = false;
        try {
            double n = convert(baseCurrency, tmpCurrency, Double.parseDouble(temp));
            String newtmp = String.format("%s",n);
            operand.add(NumberFormat.getInstance().parse(newtmp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(operand.size() > 1){
            super.evalute(currentOperation);
            tmpCurrency = baseCurrency;
        }
    }


}
