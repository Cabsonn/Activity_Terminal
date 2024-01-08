package GPS.Commands;

import GPS.Prompt;
import GPS.SocialManager;
import GPS.Error;

public class LogOut extends Command {
    public LogOut(SocialManager socialManager, Prompt prompt) {
        super("logout", 0,socialManager, prompt);
    }

    public boolean parse(String command) {
        return command.equals(name);
    }
    public boolean checkParams (String command){
        return true;
    }

    public void execute(){
        Error error = socialManager.logOut();
        if(error.isNull()){
            this.updatePath();
        }
        error.writeln();

    }
    public void updatePath(){
        this.prompt.changePath("gps>");
    }

}