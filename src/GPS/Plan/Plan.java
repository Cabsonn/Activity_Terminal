package GPS.Plan;

import java.text.SimpleDateFormat;
import java.util.Date;

import GPS.Activity.ActivityCollection;
import GPS.User.User;
import GPS.User.UserCollection;

public class Plan {
    private  int cost;
    private  int duration;
    private final int id;
    private final String name;
    private final Date date;
    private final String meetPlace;
    private int capacity;
    private User creator;
    private final ActivityCollection activityCollection;
    private int availableSlots;
    private final UserCollection userCollection;
    private final int BREAKTIME = 20;


    public Plan(int id, String name, Date date, String meetPlace, int capacity, User creator) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.meetPlace = meetPlace;
        this.capacity = capacity;
        this.creator = creator;
        this.activityCollection = new ActivityCollection();
        this.userCollection = new UserCollection();
        this.duration = 0;
        this.cost = 0;
        this.availableSlots = capacity;
    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public User getCreator(){
        return creator;
    }
    public Date getDate(){
        return this.date;
    }
    public int getCapacity(){
        return this.capacity;
    }
    public String getMeetPlace(){
        return this.meetPlace;
    }
    public int getAvailableSlots(){
        return this.availableSlots;
    }
    public UserCollection getUserCollection(){
        return this.userCollection;
    }
    public ActivityCollection getActivityCollection(){
        return this.activityCollection;
    }
    public int getDuration(){
        return this.duration;
    }
    public int getCost(){
        return this.cost;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public void setDuration(int duration){
        if(this.activityCollection.size()==1){
            this.duration=duration;
        }else{
            this.duration+=duration+this.BREAKTIME;
        }
    }
    public void setNullCreator(){
        this.creator = null;
    }
    public void minusAvailableSlots(){
        this.availableSlots --;
    }
    public void plusAvailableSlots(){
        this.availableSlots++;
    }

    public void setCost(int cost){
        this.cost+=cost;
    }
    public String dateToString(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH.mm");
        return format.format(this.date);
    }

    public String getSummary(){
        return "id:"+this.id+"; propietario:"+this.getCreator().getUsername()+"; nombre:"+this.name+"; fecha: "+this.dateToString()+
                "; lugar: "+this.meetPlace+"; aforo:"+this.capacity+"; duración:"+this.duration+" min; coste:"+this.cost+
                "€; \n"+this.activityCollection.listActivities();
    }

}


