package GPS.User;

import GPS.Plan.PlanCollection;
import GPS.Error;

public class User {
    private final int id;
    private String username;
    private final int age;
    private final String phone;
    private String password;
    private final PlanCollection planCollection;
    private final PlanCollection plansJoined;

    public User(int id, String username, int age, String phone, String password) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.phone = phone;
        this.password = password;
        this.planCollection = new PlanCollection();
        this.plansJoined = new PlanCollection();
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
    public int getAge(){
        return this.age;
    }
    public PlanCollection getPlansCollection(){
        return this.planCollection;
    }
    public PlanCollection getPlansJoined(){
        return this.plansJoined;
    }
    public int getId(){
        return this.id;
    }
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    public void setUsername(String newUsername){
        this.username = newUsername;
    }


    private static final int MIN_AGE = 14;
    private static final int MAX_AGE = 100;

    public static Error checkAge(int age) {
        Error error= Error.NULL;
        if (age < MIN_AGE || age > MAX_AGE) {
            return Error.AGE_OUT_OF_RANGE;
        }
        error.writeln();
        return error;
    }

    private static final int MIN_CHARACTERS = 3;

    public static Error checkPassword(String password) {
        Error error = Error.NULL;
        if (password.length() < MIN_CHARACTERS) {
            return Error.INSUFFICIENT_PASSWORD_LENGTH;
        }
        error.writeln();
        return error;
    }


}

