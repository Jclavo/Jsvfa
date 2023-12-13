package samples.JSVFA.cases.callGraph;

public class CallGraph2 {

    public void main(String[] var0)
    {
//        String p1Name;

        Person p1 = new Person();
        p1.setName("micho");
//        p1Name = p1.getName();


        Person p2 = new Person();
        p2.setName("bayzon");
    }
}

class Person {

//    int age;
    String name;

    public void setName(String _name) {
        this.name = _name;
    }

    public String getName() {
        return this.name;
    }
}
