package samples.JSVFA.rules.call;

public class simpleCallWithoutParameters {

    public void main(String[] var0)
    {
        int a, b, result;

        a = 1;
        b = 2;

        result = a + b;

        showLog();

        System.out.println(result);
    }

    public void showLog()
    {
        int seg;

        seg = 0;
        System.out.println(seg);
    }
}

/**
 * {
 *     samples.JSVFA.rules.call.simpleCallWithoutParameters r0;
 *     int $i0, $i1, $i2, $i3;
 *     java.io.PrintStream $r2;
 *     java.lang.String[] $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.simpleCallWithoutParameters;
 *     $r1 := @parameter0: java.lang.String[];
 *     $i0 = 0;
 *     $i1 = 0;
 *     $i2 = 0;
 *     $i0 = 1;
 *     $i1 = 2;
 *     $i3 = $i0 + $i1;
 *     $i2 = $i3;
 *     virtualinvoke r0.<samples.JSVFA.rules.call.simpleCallWithoutParameters: void showLog()>();
 *     $r2 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r2.<java.io.PrintStream: void println(int)>($i2);
 *
 *     return;
 * }
 *
 * {
 *     samples.JSVFA.rules.call.simpleCallWithoutParameters r0;
 *     int $i0;
 *     java.io.PrintStream $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.simpleCallWithoutParameters;
 *     $i0 = 0;
 *     $i0 = 0;
 *     $r1 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r1.<java.io.PrintStream: void println(int)>($i0);
 *
 *     return;
 * }
 *
 * {
 *     samples.JSVFA.rules.call.simpleCallWithoutParameters r0;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.simpleCallWithoutParameters;
 *     specialinvoke r0.<java.lang.Object: void <init>()>();
 *
 *     return;
 * }
 */