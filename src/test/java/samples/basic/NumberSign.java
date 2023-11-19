package samples.basic;

import java.util.Random;

public class NumberSign {

    public static void main(String[] var0)
    {
        int min, max, n;
        String message;

        min = -100;
        max = 100;
        n = new Random().nextInt(max - min) + min;

        if (n > 0) {
            message = "positive";
        }
        else {
            if (n == 0) {
                message = "zero";
            }
            else {
                message = "negative";
            }
        }

        System.out.println(message);
    }
}