package checkpoint.andela.com.currencycalculator.Model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import checkpoint.andela.com.currencycalculator.CurrencyParser.Currency;
import checkpoint.andela.com.currencycalculator.CurrencyParser.CurrencyParser;

/**
 * Created by andela-cj on 9/23/15.
 */
public class CurrencyConverter {
    public Calculator calculator;
    private ArrayList<String> inputHistory;

    public CurrencyConverter(){
        super();
        calculator = new Calculator();
        inputHistory = new ArrayList<>();
    }

    private String baseCurrency = CurrencyParser.baseCurrency;
    private String tempCurrency = baseCurrency;

    public String getTempCurrency() {
        return tempCurrency;
    }

    public void setTempCurrency(String tempCurrency) {
        Currency cr = CurrencyParser.getCurrencyRate(tempCurrency);
        this.tempCurrency = cr.getCurrency();
    }

    public double convert(double amount){
        if (!baseCurrency.equals(tempCurrency)) {
            double currentInBase = convertToBase(amount);
            Currency cr = CurrencyParser.getCurrencyRate(tempCurrency);
            return currentInBase / cr.getRate();
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
        if(calculator.isEnded){
            double n;
            try {
                n = convert(NumberFormat.getInstance().parse(calculator.getResult()).doubleValue());
                String newTemp = NumberFormat.getInstance().format(n);
                calculator.setTemp(newTemp);
                calculator.processOperandStack();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateInputHistoryWithOperation(String operation){
        if (calculator.isEnded)
            inputHistory.add(getTempCurrency()+ " " + calculator.getResult());
        else  {
            inputHistory = new ArrayList<>();
            inputHistory.add(getBaseCurrency()+ " " + calculator.getResult());
        }
        inputHistory.add(operation);
    }

    public ArrayList<String> getInputHistory(){
        return inputHistory;
    }

    public void setOperation(String operation) {
        calculator.currentOperation = calculator.getOperation(operation);
    }

    public void clearInputHistory() {
        inputHistory = new ArrayList<>();
    }
    public Calculator getCalculator(){return calculator;}
}
