package br.unb.cic.sootup.resources.JSVFA.rules;

public class SimpleAssignment {
    /**
     * s1: a = 1
     * s2: b = 2
     * s3: c = a
     * s4: d = b + c
     * s5: e = c
     */
    public void main(String[] var0)
    {
        int a, b, c, d, e;

        a = 1;
        b = 2;
        c = a;
        e = c;
    }
}
