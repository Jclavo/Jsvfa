package samples.JSVFA.rules.call;

public class simpleCallAndReturn {

    public void main(String[] var0)
    {
        int a, b, result, abs;

        a = 1;
        b = 2;

        result = sum(a, b);
        System.out.println(result);
    }

    public int sum(int x, int y)
    {
        int r;

        r = x + y;
        return r;
    }
}

/**
 *
 * main
 * {
 *     samples.JSVFA.rules.call.simpleCallAndReturn r0;
 *     int $i0, $i1, $i2, $i3, $i4;
 *     java.io.PrintStream $r2;
 *     java.lang.String[] $r1;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.simpleCallAndReturn;
 *     $r1 := @parameter0: java.lang.String[];
 *     $i0 = 0;
 *     $i1 = 0;
 *     $i2 = 0;
 *     $i3 = 0;
 *     $i0 = 1;
 *     $i1 = 2;
 *     $i4 = virtualinvoke r0.<samples.JSVFA.rules.call.simpleCallAndReturn: int sum(int,int)>($i0, $i1);
 *     $i2 = $i4;
 *     $r2 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r2.<java.io.PrintStream: void println(int)>($i2);
 *
 *     return;
 * }
 *
 * sum
 * {
 *     samples.JSVFA.rules.call.simpleCallAndReturn r0;
 *     int $i0, $i1, $i2, $i3;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.simpleCallAndReturn;
 *     $i0 := @parameter0: int;
 *     $i1 := @parameter1: int;
 *     $i2 = 0;
 *     $i3 = $i0 + $i1;
 *     $i2 = $i3;
 *
 *     return $i2;
 * }
 */