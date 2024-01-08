package GPS.Activity;

public abstract class ActivityType {
    protected int age;

    protected String name;
    public double discount;

    public ActivityType(String name, int age){
        this.name = name;
        this.age = age;
        this.discount = 0;
    }
}
