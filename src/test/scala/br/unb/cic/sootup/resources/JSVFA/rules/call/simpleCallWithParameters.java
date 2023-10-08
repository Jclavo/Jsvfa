package br.unb.cic.sootup.resources.JSVFA.rules.call;

public class simpleCallWithParameters {

    public void main(String[] var0)
    {
        int a, b;

        a = 1;
        b = 2;

        sum(a, b);
    }

    public void sum(int x, int y)
    {
        int res;

        res = x + y;

        System.out.println(res);
    }
}

/**
 * main
 * {
 *     br.unb.cic.sootup.resources.JSVFA.rules.call.simpleCallWithParameters r0;
 *     int $i0, $i1;
 *     java.lang.String[] $r1;
 *
 *
 *     r0 := @this: br.unb.cic.sootup.resources.JSVFA.rules.call.simpleCallWithParameters;
 *     $r1 := @parameter0: java.lang.String[];
 *     $i0 = 0;
 *     $i1 = 0;
 *     $i0 = 1;
 *     $i1 = 2;
 *     virtualinvoke r0.<br.unb.cic.sootup.resources.JSVFA.rules.call.simpleCallWithParameters: void sum(int,int)>($i0, $i1);
 *
 *     return;
 * }
 *
 * sum
 * {
 *     br.unb.cic.sootup.resources.JSVFA.rules.call.simpleCallWithParameters r0;
 *     int $i0, $i1, $i2, $i3;
 *     java.io.PrintStream $r1;
 *
 *
 *     r0 := @this: br.unb.cic.sootup.resources.JSVFA.rules.call.simpleCallWithParameters;
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
 * <init>
 * {
 *     br.unb.cic.sootup.resources.JSVFA.rules.call.simpleCallWithParameters r0;
 *
 *
 *     r0 := @this: br.unb.cic.sootup.resources.JSVFA.rules.call.simpleCallWithParameters;
 *     specialinvoke r0.<java.lang.Object: void <init>()>();
 *
 *     return;
 * }
 */