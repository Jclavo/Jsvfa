package samples.basic;

public class CalculatorSimple {

    /**
     * op
     * 1 = addition
     * 2 = subtraction
     * 3 = multiplication
     * 4 = division
     */
    public void main(String[] var0)
    {
        int result;
        int op = 1;
        int value1 = 1;
        int value2 = 2;

        if (op == 1) {
            result = value1 + value2;
        }
        else if (op == 2) {
            result = value1 - value2;
        } else {
            result = 0;
        }

        System.out.println(result);
    }
}