package GPS.Activity;

public class Activity {
    private final ActivityType activityType;
    private final int id;
    private final String name;
    private final String description;
    private final int duration;
    private final int cost;
    private final int capacity;

    public Activity(int id, ActivityType activityType, String name, String description, int duration, int cost, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.cost = cost;
        this.capacity = capacity;
        this.activityType = activityType;

    }
    public int getId(){
        return this.id;
    }
    public int getCapacity(){
        return this.capacity;
    }

    public String getDescription(){
        return this.description;
    }
    public String getName(){
        return this.name;
    }
    public int getDuration(){
        return duration;
    }
    public int getCost(){
        return this.cost;
    }
}

