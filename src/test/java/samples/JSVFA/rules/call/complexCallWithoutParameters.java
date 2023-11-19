package samples.JSVFA.rules.call;

public class complexCallWithoutParameters {

    public void main(String[] var0)
    {
        int a, b, resAdd, ResSub, resMul;

        a = 1;
        b = 2;

        resAdd = a + b;

        showLog();
        System.out.println(resAdd);

        ResSub = a - b;

        showSpecialLog();
        System.out.println(ResSub);

        resMul = a * b;

        showSpecialLog();
        System.out.println(resMul);
    }

    public void showLog()
    {
        int seg;

        seg = 0;
        System.out.println(seg);
    }

    public void showSpecialLog()
    {
        int seg;

        seg = 69;
        System.out.println(seg);
    }
}

/**
 * {
 *     samples.JSVFA.rules.call.complexCallWithoutParameters r0;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallWithoutParameters;
 *     specialinvoke r0.<java.lang.Object: void <init>()>();
 *
 *     return;
 * }
 *
 * {
 *     samples.JSVFA.rules.call.complexCallWithoutParameters r0;
 *     int $i0;
 *     java.io.PrintStream $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallWithoutParameters;
 *     $i0 = 0;
 *     $i0 = 0;
 *     $r1 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r1.<java.io.PrintStream: void println(int)>($i0);
 *
 *     return;
 * }
 *
 * {
 *     samples.JSVFA.rules.call.complexCallWithoutParameters r0;
 *     int $i0, $i1, $i2, $i3, $i4, $i5, $i6, $i7;
 *     java.io.PrintStream $r2, $r3, $r4;
 *     java.lang.String[] $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallWithoutParameters;
 *     $r1 := @parameter0: java.lang.String[];
 *     $i0 = 0;
 *     $i1 = 0;
 *     $i2 = 0;
 *     $i3 = 0;
 *     $i4 = 0;
 *     $i0 = 1;
 *     $i1 = 2;
 *     $i5 = $i0 + $i1;
 *     $i2 = $i5;
 *     virtualinvoke r0.<samples.JSVFA.rules.call.complexCallWithoutParameters: void showLog()>();
 *     $r2 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r2.<java.io.PrintStream: void println(int)>($i2);
 *     $i6 = $i0 - $i1;
 *     $i3 = $i6;
 *     virtualinvoke r0.<samples.JSVFA.rules.call.complexCallWithoutParameters: void showSpecialLog()>();
 *     $r3 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r3.<java.io.PrintStream: void println(int)>($i3);
 *     $i7 = $i0 * $i1;
 *     $i4 = $i7;
 *     virtualinvoke r0.<samples.JSVFA.rules.call.complexCallWithoutParameters: void showSpecialLog()>();
 *     $r4 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r4.<java.io.PrintStream: void println(int)>($i4);
 *
 *     return;
 * }
 *
 * {
 *     samples.JSVFA.rules.call.complexCallWithoutParameters r0;
 *     int $i0;
 *     java.io.PrintStream $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexCallWithoutParameters;
 *     $i0 = 0;
 *     $i0 = 69;
 *     $r1 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r1.<java.io.PrintStream: void println(int)>($i0);
 *
 *     return;
 * }
 */