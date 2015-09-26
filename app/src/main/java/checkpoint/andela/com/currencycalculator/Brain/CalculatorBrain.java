package checkpoint.andela.com.currencycalculator.Brain;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by andela-cj on 9/21/15.
 */
public class CalculatorBrain {
    private final int last = 0;
    protected String temp = "";
    private Hashtable<String, Operation> operation = new Hashtable<>();
    protected ArrayList<Number> operand = new ArrayList<>();
    public Operation currentOperation;
    boolean isTyping = false;

    public CalculatorBrain(){
        setupOperation();
    }

    private void setupOperation() {
        operation.put("+",Operation.add);
        operation.put("−",Operation.minus);
        operation.put("÷",Operation.divide);
        operation.put("×", Operation.multiply);
    }

    protected void evaluate(Operation a){
        double result = 0;
        switch (a){
            case divide:
                result = operand.remove(last).doubleValue() / operand.remove(last).doubleValue();
                break;
            case minus:
                result = operand.remove(last).doubleValue() - operand.remove(last).doubleValue();
                break;
            case multiply:
                result = operand.remove(last).doubleValue() * operand.remove(last).doubleValue();
                break;
            case add:
                result = operand.remove(last).doubleValue() + operand.remove(last).doubleValue();
                break;
        }
        temp = NumberFormat.getInstance().format(result);
    }

    public void evaluate(){
        if(currentOperation != null && isTyping)
          toggleIsTyping();
    }

    public Operation getOperation(String op){
        return operation.get(op);
    }
    
    public void addDigit(String digit){
        if(isTyping){
            temp+=digit;
        }else{
            temp = digit;
            isTyping = true;
        }
    }

    public void addDecimalPoint(){
        if (isTyping) {
            final String decimal = ".";
            if (!temp.contains(decimal)) temp += decimal;
        }else {
            clear();
        }
    }

    public void negateDigit(){
        final String minus = "-";
        if(temp.startsWith(minus)) temp = temp.replace(minus,"");
        else  temp = minus + temp;
    }

    public void clear (){
        operand = new ArrayList<>();
        currentOperation = null;
        isTyping = false;
        temp = "0";
    }

    public void toggleIsTyping() {
        isTyping = false;
        try {
            operand.add(NumberFormat.getInstance().parse(temp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(operand.size() >1 ){
            evaluate(currentOperation);
        }
    }

    public String getResult(){
        return temp;
    }

    private enum Operation{
        multiply, divide, minus, add
    }
}
