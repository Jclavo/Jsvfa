package br.unb.cic.sootup.resources.JSVFA.rules.pointers;

class classA {
    public int value;
}
public class SimplePointerAssignment {

    public static void main(String[] var0)
    {
        classA p, q;

        p = new classA();
        q = p;
        System.out.println(q);
    }
}

/**
 * {
 *     br.unb.cic.sootup.resources.JSVFA.rules.pointers.classA $r1;
 *     java.io.PrintStream $r2;
 *     java.lang.String[] $r0;
 *     unknown $u0, $u1;
 *
 *
 *     $r0 := @parameter0: java.lang.String[];
 *     $u0 = null;
 *     $u1 = null;
 *     $r1 = new br.unb.cic.sootup.resources.JSVFA.rules.pointers.classA;
 *     specialinvoke $r1.<br.unb.cic.sootup.resources.JSVFA.rules.pointers.classA: void <init>()>();
 *     $u0 = $r1;
 *     $u1 = $u0;
 *     $r2 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r2.<java.io.PrintStream: void println(java.lang.Object)>($u1);
 *
 *     return;
 * }
 *
 * {
 *     br.unb.cic.sootup.resources.JSVFA.rules.pointers.SimplePointerAssignment r0;
 *
 *
 *     r0 := @this: br.unb.cic.sootup.resources.JSVFA.rules.pointers.SimplePointerAssignment;
 *     specialinvoke r0.<java.lang.Object: void <init>()>();
 *
 *     return;
 * }
 */
