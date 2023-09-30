package br.unb.cic.sootup.resources.JSVFA.rules;

public class ComplexVariableAssignment {

    public void main(String[] var0)
    {
        int a, b, c;

        a = 1;
        b = 2;
        c = a + b;
    }
}

/**
 * br.unb.cic.sootup.resources.JSVFA.rules.ComplexVariableAssignment r0;
 * int $i0, $i1, $i2, $i3;
 * java.lang.String[] $r1;
 *
 *
 * r0 := @this: br.unb.cic.sootup.resources.JSVFA.rules.ComplexVariableAssignment;
 * $r1 := @parameter0: java.lang.String[];
 * $i0 = 0;
 * $i1 = 0;
 * $i2 = 0;
 * $i0 = 1;
 * $i1 = 2;
 * $i3 = $i0 + $i1;
 * $i2 = $i3;
 *
 * return;
 */


