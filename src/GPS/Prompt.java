package GPS;

public class Prompt {
    public String path;
    public Prompt(){
        this.path = "gps> ";
    }

    public String getPath(){
        return this.path;
    }
    public void changePath(String path){
        this.path = path;
    }
}

