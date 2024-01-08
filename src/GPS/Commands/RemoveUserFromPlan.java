package GPS.Commands;

import GPS.Error;
import GPS.Prompt;
import GPS.SocialManager;
import GPS.User.User;

public class RemoveUserFromPlan extends Command {
    private String[] params;

    public RemoveUserFromPlan(SocialManager socialManager, Prompt prompt) {
        super("remove-user", 2, socialManager,prompt);
    }

    private String[] getCommandParts(String command) {
        return command.split(":");
    }

    public boolean parse(String command) {
        String commandName = this.getCommandParts(command)[0];
        return commandName.equals(name);
    }

    public boolean checkParams(String command){
        String arguments = this.getCommandParts(command)[1];
        this.params = arguments.split(";");
        return params.length == expectedParameters;
    }

    public void execute() {
       Error error = socialManager.checkRemoveUser(Integer.parseInt(this.params[0]),this.params[1]);
       User user;
       if(error.isNull()){
           user = socialManager.removeUser(Integer.parseInt(this.params[0]),this.params[1]);
           this.showMessage(user, Integer.parseInt(this.params[1]));
       }else{
           error.writeln();
       }
    }
    private void showMessage(User user, int planId) {
        System.out.println("Se ha eliminado correctamente el usuario "+user.getUsername()+ " del plan con id: "+planId);
    }
}

