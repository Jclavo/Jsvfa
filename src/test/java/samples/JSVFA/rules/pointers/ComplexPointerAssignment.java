package samples.JSVFA.rules.pointers;

class classX {
    public int n;
}

class classY {
    public int n;
}

public class ComplexPointerAssignment {

    public static void main(String[] var0)
    {
        classX p, q, r;
        classY a, b;

        p = new classX();
        a = new classY();

        q = p;
        b = a;
        r = q;
        
        System.out.println(b);
        System.out.println(r);
    }
}

/**
 * {
 *     samples.JSVFA.rules.pointers.classX $r1;
 *     samples.JSVFA.rules.pointers.classY $r2;
 *     java.io.PrintStream $r3, $r4;
 *     java.lang.String[] $r0;
 *     unknown $u0, $u1, $u2, $u3, $u4;
 *
 *
 *     $r0 := @parameter0: java.lang.String[];
 *     $u0 = null;
 *     $u1 = null;
 *     $u2 = null;
 *     $u3 = null;
 *     $u4 = null;
 *     $r1 = new samples.JSVFA.rules.pointers.classX;
 *     specialinvoke $r1.<samples.JSVFA.rules.pointers.classX: void <init>()>();
 *     $u0 = $r1;
 *     $r2 = new samples.JSVFA.rules.pointers.classY;
 *     specialinvoke $r2.<samples.JSVFA.rules.pointers.classY: void <init>()>();
 *     $u3 = $r2;
 *     $u1 = $u0;
 *     $u4 = $u3;
 *     $u2 = $u1;
 *     $r3 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r3.<java.io.PrintStream: void println(java.lang.Object)>($u4);
 *     $r4 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r4.<java.io.PrintStream: void println(java.lang.Object)>($u2);
 *
 *     return;
 * }
 *
 * {
 *     samples.JSVFA.rules.pointers.ComplexPointerAssignment r0;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.pointers.ComplexPointerAssignment;
 *     specialinvoke r0.<java.lang.Object: void <init>()>();
 *
 *     return;
 * }
 */
