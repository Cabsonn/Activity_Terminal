package GPS;

import GPS.Activity.Activity;
import GPS.Managers.ActivityManager;
import GPS.Plan.Plan;
import GPS.Managers.PlanManager;
import GPS.User.User;
import GPS.Managers.UserManager;

import java.util.Date;

public class SocialManager {
    private final UserManager userManager;
    private final ActivityManager activityManager;
    private final PlanManager planManager;
    private final CLI cli;


    public SocialManager() {
        this.userManager = new UserManager();
        this.planManager = new PlanManager();
        this.activityManager = new ActivityManager();
        this.cli = new CLI(this);
    }
    public Error check_In(String username, int age, String phone, String password){
        return this.userManager.check_In(username,age,phone,password);
    }
    public User addUser(String username, int age, String phone, String password){
        return this.userManager.addUser(username,age,phone,password);
    }
    public Error login(String username, String password){
        return this.userManager.login(username,password);
    }
    public User findUser(String username, String password){
        return this.userManager.findUser(username,password);
    }
    public Error logOut(){
        return this.userManager.logOut();
    }
    public Error checkActivity(String type){
        return this.activityManager.checkActivity(type, this.userManager);
    }
    public Activity createActivity (String type, String name, String description, int duration, double cost, int capacity){
        return this.activityManager.createActivity(type,name,description,duration,cost,capacity,this.userManager);
    }
    public Activity createActivityNoLimit(String type, String name, String description, int duration, double cost){
        return this.activityManager.createActivityNoLimit(type,name,description,duration,cost,this.userManager);
    }
    public Error checkCreatePlan(){
        return this.planManager.checkCreatePlan(this.userManager);
    }
    public Plan createPlan(String name, Date date, String meetPlace, int capacity){
        return this.planManager.createPlan(name,date,meetPlace,capacity,this.userManager);
    }
    public Error checkAddActivityToPlan(int idPlan, int idActivity){
        return this.planManager.checkAddActivityToPlan(idPlan,idActivity,this.userManager,this.activityManager);
    }
    public Plan addActivityToPlan(int idPlan, int idActivity){
        return this.planManager.addActivityToPlan(idPlan,idActivity,this.activityManager);
    }
    public Error listPlans(String param){
        return this.planManager.listPlans(param,this.userManager);
    }
    public Error checkJoinPlan(int idPlan){
        return this.planManager.checkJoinPlan(idPlan,this.userManager);
    }
    public Plan joinPlan(int idPlan){
        return this.planManager.joinPlan(idPlan,this.userManager);
    }
    public Error checkLeavePlan(int idPlan){
        return this.planManager.checkLeavePlan(idPlan,this.userManager);
    }
    public Plan leavePlan(int idPlan){
        return this.planManager.leavePlan(idPlan,this.userManager);
    }
    public Error checkClosePlan(int idPlan){
        return this.planManager.checkClosePlan(idPlan,this.userManager);
    }
    public Plan closePlan(int idPlan){
        return this.planManager.closePlan(idPlan);
    }


    public static void main(String[] args) {
        SocialManager socialManager = new SocialManager();
        socialManager.cli.start();
    }
}
