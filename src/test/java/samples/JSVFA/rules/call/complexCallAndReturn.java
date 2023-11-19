package samples.JSVFA.rules.call;

public class complexCallAndReturn {

    public void main(String[] var0)
    {
        int a, b, c, d, e, f, result;

        a = 1;
        b = 2;
        result = sum(a, b);
        System.out.println(result);

        c = 10;
        d = 5;
        result = sub(c, d);
        System.out.println(result);

        e = 3;
        f = 4;
        result = sum(e, f);
        System.out.println(result);
    }

    public int sum(int x, int y)
    {
        int res;

        res = x + y;

        return res;
    }

    public int sub(int x, int y)
    {
        int res;

        res = x - y;

        return res;
    }
}

/**
 * sum
 * {
 *     samples.JSVFA.rules.call.complexCallAndReturn r0;
 *     int $i0, $i1, $i2, $i3;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallAndReturn;
 *     $i0 := @parameter0: int;
 *     $i1 := @parameter1: int;
 *     $i2 = 0;
 *     $i3 = $i0 + $i1;
 *     $i2 = $i3;
 *
 *     return $i2;
 * }
 *
 * sub
 * {
 *     samples.JSVFA.rules.call.complexCallAndReturn r0;
 *     int $i0, $i1, $i2, $i3;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallAndReturn;
 *     $i0 := @parameter0: int;
 *     $i1 := @parameter1: int;
 *     $i2 = 0;
 *     $i3 = $i0 - $i1;
 *     $i2 = $i3;
 *
 *     return $i2;
 * }
 *
 * main
 * {
 *     samples.JSVFA.rules.call.complexCallAndReturn r0;
 *     int $i0, $i1, $i2, $i3, $i4, $i5, $i6, $i7, $i8, $i9;
 *     java.io.PrintStream $r2, $r3, $r4;
 *     java.lang.String[] $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallAndReturn;
 *     $r1 := @parameter0: java.lang.String[];
 *     $i0 = 0;
 *     $i1 = 0;
 *     $i2 = 0;
 *     $i3 = 0;
 *     $i4 = 0;
 *     $i5 = 0;
 *     $i6 = 0;
 *     $i0 = 1;
 *     $i1 = 2;
 *     $i7 = virtualinvoke r0.<samples.JSVFA.rules.call.complexCallAndReturn: int sum(int,int)>($i0, $i1);
 *     $i6 = $i7;
 *     $r2 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r2.<java.io.PrintStream: void println(int)>($i6);
 *     $i2 = 10;
 *     $i3 = 5;
 *     $i8 = virtualinvoke r0.<samples.JSVFA.rules.call.complexCallAndReturn: int sub(int,int)>($i2, $i3);
 *     $i6 = $i8;
 *     $r3 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r3.<java.io.PrintStream: void println(int)>($i6);
 *     $i4 = 3;
 *     $i5 = 4;
 *     $i9 = virtualinvoke r0.<samples.JSVFA.rules.call.complexCallAndReturn: int sum(int,int)>($i4, $i5);
 *     $i6 = $i9;
 *     $r4 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r4.<java.io.PrintStream: void println(int)>($i6);
 *
 *     return;
 * }
 */