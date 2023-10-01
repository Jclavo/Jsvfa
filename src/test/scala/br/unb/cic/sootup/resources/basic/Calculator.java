package br.unb.cic.sootup.resources.basic;

public class Calculator {

    public void main(String[] var0)
    {
        int a = 1;
        int b = 2;
        int sum = 0;
        int sub = 0;

        sum = this.sum(a, b);
        System.out.println(sum);

        sub = this.sub(a, b);
        System.out.println(sub);
    }

    public int sum(int a, int b)
    {
        int c = a + b;
        return c;
    }

    public int sub(int a, int b)
    {
        int c = a - b;
        return c;
    }
}