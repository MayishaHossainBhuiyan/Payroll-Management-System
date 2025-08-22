package Project;

abstract public class Person {

    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract public String getName();

    abstract public int getAge();

    abstract public void setName(String name);

    abstract public void setAge(int age);

}
