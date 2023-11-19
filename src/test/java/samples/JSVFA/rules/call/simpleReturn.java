package samples.JSVFA.rules.call;

public class simpleReturn {

    public void main(String[] var0)
    {
        int a, b, result;

        a = 1;

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
 * main
 * {
 *     br.unb.cic.sootup.resources.JSVFA.rules.call.simpleReturn r0;
 *     int $i0, $i1, $i2, $i3, $i4;
 *     java.io.PrintStream $r2;
 *     java.lang.String[] $r1;
 *
 *
 *     r0 := @this: br.unb.cic.sootup.resources.JSVFA.rules.call.simpleReturn;
 *     $r1 := @parameter0: java.lang.String[];
 *     $i0 = 0;
 *     $i1 = 0;
 *     $i2 = 0;
 *     $i0 = 1;
 *     $i3 = virtualinvoke r0.<br.unb.cic.sootup.resources.JSVFA.rules.call.simpleReturn: int getMagicNumber()>();
 *     $i1 = $i3;
 *     $i4 = $i0 + $i1;
 *     $i2 = $i4;
 *     $r2 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r2.<java.io.PrintStream: void println(int)>($i2);
 *
 *     return;
 * }
 *
 * <init>
 * {
 *     br.unb.cic.sootup.resources.JSVFA.rules.call.simpleReturn r0;
 *
 *
 *     r0 := @this: br.unb.cic.sootup.resources.JSVFA.rules.call.simpleReturn;
 *     specialinvoke r0.<java.lang.Object: void <init>()>();
 *
 *     return;
 * }
 *
 * getMagicNumber
 * {
 *     br.unb.cic.sootup.resources.JSVFA.rules.call.simpleReturn r0;
 *     int $i0;
 *
 *
 *     r0 := @this: br.unb.cic.sootup.resources.JSVFA.rules.call.simpleReturn;
 *     $i0 = 0;
 *     $i0 = 69;
 *
 *     return $i0;
 * }
 */