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
   public Error checkActivity(UserManager userManager){
       if(userManager.getAuthenticated()==null){
           return Error.NOT_LOGGED_USER;
       }
       return Error.NULL;
   }
    public Activity createActivity (ActivityType activityType, String name, String description, int duration, int cost, int capacity){
        Activity activity = new Activity(this.id,activityType, name, description, duration, cost, capacity);
        this.activityCollection.add(activity);
        this.id++;
        return activity;
    }
    public Activity createActivityNoLimit(ActivityType activityType, String name, String description, int duration, int cost){
        Activity activity = new Activity(this.id,activityType, name, description, duration, cost, -1);
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

