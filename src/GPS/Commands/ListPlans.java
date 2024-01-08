package GPS.Commands;

import GPS.Prompt;
import GPS.SocialManager;

public class ListPlans extends Command {
    private String[] params;

    public ListPlans(SocialManager socialManager, Prompt prompt) {
        super("list-plans", 1, socialManager,prompt);
    }
    private String[] getCommandParts(String command){
        return command.split(":",2);
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

    public void execute() {
        socialManager.listPlans(this.params[0]).writeln();
    }

}
