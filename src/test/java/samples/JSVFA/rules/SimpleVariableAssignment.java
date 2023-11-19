package samples.JSVFA.rules;

public class SimpleVariableAssignment {

    public void main(String[] var0)
    {
        int a, b, c, d, e;

        a = 1;
        b = 2;
        c = a;
        d = a;
        e = b;
    }
}

/**
 *     samples.JSVFA.rules.SimpleVariableAssignment r0;
 *     int $i0, $i1, $i2, $i3, $i4;
 *     java.lang.String[] $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.SimpleVariableAssignment;
 *     $r1 := @parameter0: java.lang.String[];
 *     $i0 = 0;
 *     $i1 = 0;
 *     $i2 = 0;
 *     $i3 = 0;
 *     $i4 = 0;
 *     $i0 = 1;
 *     $i1 = 2;
 *     $i2 = $i0;
 *     $i3 = $i0;
 *     $i4 = $i1;
 */
