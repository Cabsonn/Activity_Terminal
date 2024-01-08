package GPS.Commands;

import GPS.Prompt;
import GPS.SocialManager;
import GPS.Error;
import GPS.Plan.Plan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class CreatePlanCommand extends Command {
    private String[] params;
    public CreatePlanCommand(SocialManager socialManager, Prompt prompt){
        super("create-plan",4, socialManager,prompt);
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
    private Date stringToDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH.mm");
        try{
            return format.parse(this.params[1]);
        }catch (ParseException e){
            return null;
        }

    }
    public void execute(){
        Error error = this.socialManager.checkCreatePlan();
        Plan plan;
        if(error.isNull()){
            plan = socialManager.createPlan(this.params[0],this.stringToDate(),this.params[2],Integer.parseInt(params[3]));
            this.showPlan(plan);
        }else{
            error.writeln();
        }
    }
    private void showPlan(Plan plan){
        System.out.println("Plan creado: " + "id:"+plan.getId()+"; propietario:"+plan.getCreator().getUsername()+"; nombre:"+plan.getName()+
                "; fecha: "+plan.dateToString()+ "; lugar: "+plan.getMeetPlace()+"; aforo:"+plan.getCapacity()+"; duración:"+plan.getDuration()+
                " min; coste:"+plan.getCost()+ "€; \n"+plan.getActivityCollection().listActivities());
    }
}

