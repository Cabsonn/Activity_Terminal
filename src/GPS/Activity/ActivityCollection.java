package GPS.Activity;

import java.util.List;
import java.util.ArrayList;
public class ActivityCollection {
    private final List<Activity> activityCollection;
    public ActivityCollection(){
        this.activityCollection = new ArrayList<>();
    }
    public void add(Activity activity){
        this.activityCollection.add(activity);
    }
    public int size(){
        return this.activityCollection.size();
    }
    public Activity get(int i){
        return this.activityCollection.get(i);
    }
    public String listActivities(){
        String list = " ";
        if(!this.activityCollection.isEmpty()){
            for (Activity activity : this.activityCollection) {
                list = list.concat(activity.getName() + "; ");
            }
            return "Actividades: " + list;
        }
        return "Actividades: ";
    }

}
