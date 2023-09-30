package br.unb.cic.sootup.resources.JSVFA.rules;

public class FullVariableAssignment {

    public void main(String[] var0)
    {
        int a, b, c, d, e;

        a = 1;
        b = 2;
        c = a + 1;
        d = c + 0 + b;
        e = (1 + 2) * d;
    }
}

/**
 *     br.unb.cic.sootup.resources.JSVFA.rules.FullVariableAssignment r0;
 *     int $i0, $i1, $i2, $i3, $i4, $i5, $i6, $i7, $i8, $i9;
 *     java.lang.String[] $r1;
 *
 *
 *     r0 := @this: br.unb.cic.sootup.resources.JSVFA.rules.FullVariableAssignment;
 *     $r1 := @parameter0: java.lang.String[];
 *     $i0 = 0;
 *     $i1 = 0;
 *     $i2 = 0;
 *     $i3 = 0;
 *     $i4 = 0;
 *     $i0 = 1;
 *     $i1 = 2;
 *     $i5 = $i0 + 1;
 *     $i2 = $i5;
 *     $i6 = $i2 + 0;
 *     $i7 = $i6 + $i1;
 *     $i3 = $i7;
 *     $i8 = 1 + 2;
 *     $i9 = $i8 * $i3;
 *     $i4 = $i9;
 */
