package samples.JSVFA.rules.call;

public class complexCallWithParameters {

    public void main(String[] var0)
    {
        int a, b, c, d, e, f;

        a = 1;
        b = 2;
        sum(a, b);

        c = 10;
        d = 5;
        sub(c, d);

        e = 3;
        f = 4;
        sum(e, f);
    }

    public void sum(int x, int y)
    {
        int res;

        res = x + y;

        System.out.println(res);
    }

    public void sub(int x, int y)
    {
        int res;

        res = x - y;

        System.out.println(res);
    }
}

/**
 * main
 * {
 *     samples.JSVFA.rules.call.complexCallWithParameters r0;
 *     int $i0, $i1, $i2, $i3, $i4, $i5;
 *     java.lang.String[] $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallWithParameters;
 *     $r1 := @parameter0: java.lang.String[];
 *     $i0 = 0;
 *     $i1 = 0;
 *     $i2 = 0;
 *     $i3 = 0;
 *     $i4 = 0;
 *     $i5 = 0;
 *     $i0 = 1;
 *     $i1 = 2;
 *     virtualinvoke r0.<samples.JSVFA.rules.call.complexCallWithParameters: void sum(int,int)>($i0, $i1);
 *     $i2 = 10;
 *     $i3 = 5;
 *     virtualinvoke r0.<samples.JSVFA.rules.call.complexCallWithParameters: void sub(int,int)>($i2, $i3);
 *     $i4 = 3;
 *     $i5 = 4;
 *     virtualinvoke r0.<samples.JSVFA.rules.call.complexCallWithParameters: void sum(int,int)>($i4, $i5);
 *
 *     return;
 * }
 *
 * sum
 * {
 *     samples.JSVFA.rules.call.complexCallWithParameters r0;
 *     int $i0, $i1, $i2, $i3;
 *     java.io.PrintStream $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallWithParameters;
 *     $i0 := @parameter0: int;
 *     $i1 := @parameter1: int;
 *     $i2 = 0;
 *     $i3 = $i0 + $i1;
 *     $i2 = $i3;
 *     $r1 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r1.<java.io.PrintStream: void println(int)>($i2);
 *
 *     return;
 * }
 *
 * sub
 * {
 *     samples.JSVFA.rules.call.complexCallWithParameters r0;
 *     int $i0, $i1, $i2, $i3;
 *     java.io.PrintStream $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallWithParameters;
 *     $i0 := @parameter0: int;
 *     $i1 := @parameter1: int;
 *     $i2 = 0;
 *     $i3 = $i0 - $i1;
 *     $i2 = $i3;
 *     $r1 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r1.<java.io.PrintStream: void println(int)>($i2);
 *
 *     return;
 * }
 *
 * <init>
 * {
 *     samples.JSVFA.rules.call.complexCallWithParameters r0;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallWithParameters;
 *     specialinvoke r0.<java.lang.Object: void <init>()>();
 *
 *     return;
 * }
 */