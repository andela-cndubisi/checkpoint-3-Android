package checkpoint.andela.com.currencycalculator;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by andela-cj on 9/21/15.
 */
public class CalculatorBrain {
    private final int last = 0;
    private String temp = "";
    Hashtable<String, Operation> operation = new Hashtable<>();
    ArrayList<Number> operand = new ArrayList<>();
    Operation currentOperation;
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

    public void evalute(Operation a){
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

    public void enter(){
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

    public void clear (){
        operand = new ArrayList<>();
        currentOperation = null;
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
            evalute(currentOperation);
        }
    }

    public CharSequence getResult(){
        return temp;
    }

    private enum Operation{
        multiply, divide, minus, add
    }
}
