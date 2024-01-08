package GPS.Managers;

import GPS.Activity.Activity;
import GPS.Activity.ActivityCollection;
import GPS.Error;
import GPS.Activity.ActivityType;

public class ActivityManager {
    private int id;
    private final ActivityCollection activityCollection;
    public ActivityManager() {
        this.id = 1;
        this.activityCollection = new ActivityCollection();
    }
   public Error checkActivity(String type, UserManager userManager){
       if(userManager.getAuthenticated()==null){
           return Error.NOT_LOGGED_USER;
       }
       if(!type.equalsIgnoreCase("Generic") && !type.equalsIgnoreCase("Theatre") && !type.equalsIgnoreCase("Cinema")){
           return Error.ACTIVITY_TYPE;
       }
       return Error.NULL;
   }
    public Activity createActivity (String type, String name, String description, int duration, double cost, int capacity, UserManager userManager){
        Activity activity = new Activity(this.id,new ActivityType(type,userManager.getAuthenticated().getAge()), name, description, duration, cost, capacity, userManager.getAuthenticated());
        this.activityCollection.add(activity);
        this.id++;
        return activity;
    }
    public Activity createActivityNoLimit(String type, String name, String description, int duration, double cost, UserManager userManager){
        Activity activity = new Activity(this.id,new ActivityType(type,userManager.getAuthenticated().getAge()), name, description, duration, cost, -1, userManager.getAuthenticated());
        this.activityCollection.add(activity);
        this.id++;
        return activity;
    }
    public Activity searchActivityId(int actId){
        for(int i = 0; i<this.activityCollection.size();i++){
            if(this.activityCollection.get(i).getId() == actId){
                return this.activityCollection.get(i);
            }
        }
        return null;
    }

}

