package GPS.Managers;

import GPS.Activity.Activity;
import GPS.Plan.Plan;
import GPS.Plan.PlanCollection;
import GPS.Error;
import GPS.User.User;

import java.util.Date;
public class PlanManager {
    private int id;
    private final PlanCollection planCollection;


    public PlanManager() {
        this.planCollection = new PlanCollection();
        this.id = 1;
    }
    public void addPlan(Plan plan) {
        this.planCollection.add(plan);
    }
    public Error checkCreatePlan(UserManager userManager){
        if(userManager.getAuthenticated()==null){
            return Error.NOT_LOGGED_USER;
        }
        return Error.NULL;
    }
    public Plan createPlan(String name, Date date, String meetPlace, int capacity, UserManager userManager){
        Plan plan = new Plan(this.id,name,date,meetPlace,capacity,userManager.getAuthenticated());
        this.addPlan(plan);
        userManager.getAuthenticated().getPlansCollection().add(plan);
        this.id++;
        return plan;
    }
    public Plan addActivityToPlan(int idPlan, int idActivity, ActivityManager activityManager){
        Plan plan = this.searchPlanId(idPlan);
        Activity activity = activityManager.searchActivityId(idActivity);
        assert plan != null;
        plan.getActivityCollection().add(activity);
        plan.setDuration(activity.getDuration());
        plan.setCost(activity.getCost());
        if(activity.getCapacity()<plan.getCapacity() && activity.getCapacity() != -1){
            plan.setCapacity(activity.getCapacity());
        }
        return plan;
    }
    public Error checkAddActivityToPlan(int idPlan, int idActivity, UserManager userManager, ActivityManager activityManager){
        Plan plan = this.searchPlanId(idPlan);
        Activity activity = activityManager.searchActivityId(idActivity);

        if(userManager.getAuthenticated()==null){
            return Error.NOT_LOGGED_USER;
        }

        if(plan ==null){
            return Error.ID_PLAN_NOT_FOUND;
        }

        if(activity==null){
            return Error.ID_ACTIVITY_NOT_FOUND;
        }
        if(plan.getCreator() == null){
            return Error.CLOSED_PLAN;
        }

        if(plan.getCreator().equals(userManager.getAuthenticated())){
            return Error.NULL;
        }
        return Error.INSUFFICIENT_PRIVILEGES;

    }
    public Error checkClosePlan(int idPlan, UserManager userManager){
        Plan plan = this.searchPlanId(idPlan);
        if(userManager.getAuthenticated()==null){
            return Error.NOT_LOGGED_USER;
        }
        if(plan == null){
            return Error.ID_PLAN_NOT_FOUND;
        }
        if(plan.getCreator().equals(userManager.getAuthenticated())){
            return Error.NULL;
        }
        return Error.INSUFFICIENT_PRIVILEGES;
    }
    public Plan closePlan(int idPlan){
        Plan plan = this.searchPlanId(idPlan);
        assert plan != null;
        plan.setNullCreator();
        return plan;
    }
    private Plan searchPlanId(int idPlan){
        for(int i = 0; i < this.planCollection.size(); i++){
            if(idPlan == planCollection.get(i).getId()){
                return planCollection.get(i);
            }
        }
        return null;
    }

    public Error listPlans(String param, UserManager userManager){
        if(userManager.getAuthenticated()==null){
            return Error.NOT_LOGGED_USER;
        }
        if(param.equals("fecha")){
            this.planCollection.listPlans();
            return Error.NULL;
        }
        if(param.equals("joined")){
            userManager.getAuthenticated().getPlansJoined().listPlans();
            return Error.NULL;
        }
        return Error.NO_CORRECT_ORDER;
    }
    public Error checkJoinPlan(int idPlan, UserManager userManager) {
        Plan plan = this.searchPlanId(idPlan);
        if(userManager == null){return Error.NOT_LOGGED_USER;}

        if(plan ==null){return Error.ID_PLAN_NOT_FOUND;}

        if(plan.getAvailableSlots()==0){return Error.FULL_CAPACITY;}

        if(this.matchesDate(plan,userManager)){return Error.MATCHING_DATE;}

        if(this.matchesPlan(plan,userManager)){return Error.ALREADY_SUBSCRIBED;}

        return Error.NULL;
    }
    public Plan joinPlan(int idPlan, UserManager userManager){
        Plan plan = this.searchPlanId(idPlan);
        userManager.getAuthenticated().getPlansJoined().add(plan);
        assert plan != null;
        plan.getUserCollection().add(userManager.getAuthenticated());
        plan.minusAvailableSlots();
        return plan;
    }
    public Error checkLeavePlan(int idPlan, UserManager userManager){
        Plan plan = this.searchPlanId(idPlan);
        if(userManager == null){return Error.NOT_LOGGED_USER;}

        if(plan==null){return Error.ID_PLAN_NOT_FOUND;}

        if(!matchesPlan(plan,userManager)){return Error.MATCHING_PLAN;}
        return Error.NULL;
    }
    public Plan leavePlan(int idPlan, UserManager userManager){
        Plan plan = this.searchPlanId(idPlan);
        userManager.getAuthenticated().getPlansJoined().remove(plan);
        assert plan != null;
        plan.plusAvailableSlots();
        plan.getUserCollection().remove(userManager.getAuthenticated());
        return plan;
    }
    public boolean matchesDate(Plan plan, UserManager userManager){
        if(userManager.getAuthenticated().getPlansJoined()==null){
            return false;
        }
        for(int i = 0; i<userManager.getAuthenticated().getPlansJoined().size(); i++){
            if(userManager.getAuthenticated().getPlansJoined().get(i).getDate().equals(plan.getDate())){
                return true;
            }
        }
        return false;
    }
    public boolean matchesPlan(Plan plan, UserManager userManager){
        for(int i = 0; i<userManager.getAuthenticated().getPlansJoined().size(); i++){
            if(userManager.getAuthenticated().getPlansJoined().get(i).equals(plan)){
                return true;
            }
        }
        return false;
    }
    public Error checkRemoveUser(int planId, String name, UserManager userManager){
        Plan plan = userManager.getAuthenticated().getPlansCollection().searchPlanId(planId);

        if(userManager.getAuthenticated() == null){
            return Error.NOT_LOGGED_USER;
        }
        if(plan==null){
            return Error.MATCHING_PLAN;
        }
        if(plan.getUserCollection().searchUserName(name)==null){
            return Error.MATCHING_USER;
        }
        if(plan.getCreator().equals(userManager.getAuthenticated())){
            return Error.INSUFFICIENT_PRIVILEGES;
        }
        return Error.NULL;
    }
    public User removeUser(int planId, String name, UserManager userManager){
        Plan plan = userManager.getAuthenticated().getPlansCollection().searchPlanId(planId);
        User user = plan.getUserCollection().searchUserName(name);
        plan.getUserCollection().remove(user);
        return user;

    }




}
