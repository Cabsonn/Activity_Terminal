package GPS.Commands;
import GPS.SocialManager;
import GPS.Prompt;
import GPS.Plan.Plan;
import GPS.Error;

public class AddActivityPlan extends Command {
    private String[] params;
    public AddActivityPlan(SocialManager socialManager, Prompt prompt){
        super("add-activity-plan",2, socialManager,prompt);
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
    public void execute(){
        Plan plan;
        Error error = this.socialManager.checkAddActivityToPlan(Integer.parseInt(this.params[0]),Integer.parseInt(this.params[1]));
        if(error.isNull()){
            plan = socialManager.addActivityToPlan(Integer.parseInt(this.params[0]),Integer.parseInt(this.params[1]));
            this.showMessage(plan);
        }
        error.writeln();
    }
    private void showMessage(Plan plan){
        System.out.println("Actividad añadida: "+ "id:"+plan.getId()+"; propietario:"+plan.getCreator().getUsername()+"; nombre:"+plan.getName()+
                "; fecha: "+plan.dateToString()+ "; lugar: "+plan.getMeetPlace()+"; aforo:"+plan.getCapacity()+"; duración:"+plan.getDuration()+
                " min; coste:"+plan.getCost()+ "€; \n"+plan.getActivityCollection().listActivities());
    }

}

