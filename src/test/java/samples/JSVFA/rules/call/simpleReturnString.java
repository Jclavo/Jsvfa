package samples.JSVFA.rules.call;

public class simpleReturnString {

    public void main(String[] var0)
    {
        String magicWord;

        magicWord = getMagicWord();

        System.out.println(magicWord);
    }

    public String getMagicWord()
    {
        return "miau";
    }
}

/**
 * <init>
 * {
 *     samples.JSVFA.rules.call.simpleReturnString r0;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.simpleReturnString;
 *     specialinvoke r0.<java.lang.Object: void <init>()>();
 *
 *     return;
 * }
 *
 * getMagicWord
 * {
 *     samples.JSVFA.rules.call.simpleReturnString r0;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.simpleReturnString;
 *
 *     return "miau";
 * }
 *
 * main
 * {
 *     samples.JSVFA.rules.call.simpleReturnString r0;
 *     java.io.PrintStream $r3;
 *     java.lang.String $r2;
 *     java.lang.String[] $r1;
 *     unknown $u0;
 *
 *
 *     r0 := @this: samples.JSVFA.rules.call.simpleReturnString;
 *     $r1 := @parameter0: java.lang.String[];
 *     $u0 = null;
 *     $r2 = virtualinvoke r0.<samples.JSVFA.rules.call.simpleReturnString: java.lang.String getMagicWord()>();
 *     $u0 = $r2;
 *     $r3 = <java.lang.System: java.io.PrintStream out>;
 *     virtualinvoke $r3.<java.io.PrintStream: void println(java.lang.String)>($u0);
 *
 *     return;
 * }
 */