package samples.JSVFA.rules.call;

public class complexReturn {

    public void main(String[] var0)
    {
        int a, b, result;

        a = getMagicNumber();

        b = getMagicNumber();

        result = a + b;

        System.out.println(result);
    }

    public int getMagicNumber()
    {
        int magicNumber;

        magicNumber = 69;

        return magicNumber;
    }
}

/**
 *
 * getMagicNumber
 * {
 *     samples.JSVFA.rules.call.complexReturn r0;
 *     int $i0;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexReturn;
 *     $i0 = 0;
 *     $i0 = 69;
 *
 *     return $i0;
 * }
 *
 * <init>
 * {
 *     samples.JSVFA.rules.call.complexReturn r0;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexReturn;
 *     specialinvoke r0.<java.lang.Object: void <init>()>();
 *
 *     return;
 * }
 *
 * main
 * {
 *     samples.JSVFA.rules.call.complexReturn r0;
 *     int $i0, $i1, $i2, $i3, $i4, $i5;
 *     java.io.PrintStream $r2;
 *     java.lang.String[] $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.complexReturn;
 *     $r1 := @parameter0: java.lang.String[];
 *     $i0 = 0;
 *     $i1 = 0;
 *     $i2 = 0;
 *     $i3 = virtualinvoke r0.<samples.JSVFA.rules.call.complexReturn: int getMagicNumber()>();
 *     $i0 = $i3;
 *     $i4 = virtualinvoke r0.<samples.JSVFA.rules.call.complexReturn: int getMagicNumber()>();
 *     $i1 = $i4;
 *     $i5 = $i0 + $i1;
 *     $i2 = $i5;
 *     $r2 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r2.<java.io.PrintStream: void println(int)>($i2);
 *
 *     return;
 * }
 */