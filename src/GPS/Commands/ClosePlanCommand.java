package GPS.Commands;

import GPS.Plan.Plan;
import GPS.Prompt;
import GPS.SocialManager;
import GPS.Error;

public class ClosePlanCommand extends Command {
    private String[] params;

    public ClosePlanCommand(SocialManager socialManager, Prompt prompt) {
        super("close-plan", 1, socialManager,prompt);
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
        Plan plan;
        Error error = this.socialManager.checkClosePlan(Integer.parseInt(this.params[0]));
        if(error.isNull()){
            plan = this.socialManager.closePlan(Integer.parseInt(this.params[0]));
            this.showMessage(plan);
        }else{
            error.writeln();
        }
    }
    private void showMessage(Plan plan) {
        System.out.print("Se ha cerrado con éxito el plan: " + plan.getName());
        plan.getUserCollection().listUsers();
        System.out.println();
    }
}

