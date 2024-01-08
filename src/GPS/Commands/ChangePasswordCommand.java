package GPS.Commands;

import GPS.Error;
import GPS.Prompt;
import GPS.SocialManager;

public class ChangePasswordCommand extends Command {
    private String[] params;
    public ChangePasswordCommand(SocialManager socialManager, Prompt prompt){
        super("change-password",1,socialManager,prompt);
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
        Error error = socialManager.checkChangePassword();
        if(error.isNull()){
            socialManager.changePassword(params[0]);
            this.showNewPassword();
        }else{
            error.writeln();
        }

    }
    private void showNewPassword(){
        System.out.println("Contraseña cambiada con éxito a "+ this.params[0]);
    }

}


