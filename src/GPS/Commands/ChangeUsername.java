package GPS.Commands;

import GPS.Error;
import GPS.Prompt;
import GPS.SocialManager;

public class ChangeUsername extends Command {
    private String[] params;
    public ChangeUsername(SocialManager socialManager, Prompt prompt){
        super("change-username",1,socialManager,prompt);
    }
    private String[] getCommandParts(String command){
        return command.split(":");
    }
    public boolean parse(String command){
        String commandName = this.getCommandParts(command)[0];
        return commandName.equals(name);
    }
    public boolean checkParams(String command){
        String arguments = this.getCommandParts(command)[1];
        this.params = arguments.split(";");
        return params.length == expectedParameters;
    }
    public void execute(){
        Error error = socialManager.checkChangeUsername(this.params[0]);
        if(error.isNull()){
            socialManager.changeUsername(params[0]);
            this.showNewUsername();
            this.updatePath();
        }else{
            error.writeln();
        }

    }
    private void showNewUsername(){
        System.out.println("Nombre de usuario cambiado con éxito a "+ this.params[0]);
    }
    private void updatePath(){
        this.prompt.changePath("gps-"+this.params[0]+"> ");
    }

}

