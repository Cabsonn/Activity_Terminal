public class JoinPlanCommand extends Command {
    private String[] params;

    public JoinPlanCommand(SocialManager socialManager,Prompt prompt) {
        super("join-event", 1, socialManager,prompt);
    }
    private String[] getCommandParts(String command) {
        return command.split(":");
    }

    public boolean parse(String command) {
        String commandName = this.getCommandParts(command)[0];
        return commandName.equals(name);
    }

    public boolean checkParams(String command) {
        String arguments = this.getCommandParts(command)[1];
        this.params = arguments.split(";");
        return params.length == expectedParameters;
    }

    public void execute() {
        Plan plan;
        Error error = this.socialManager.checkJoinPlan(Integer.parseInt(this.params[0]));
        if(error.isNull()){
            plan = socialManager.joinPlan(Integer.parseInt(params[0]));
            this.showMessage(plan);
        }else{
            error.writeln();
        }
    }
    public void showMessage(Plan plan){
        System.out.print("Unido al plan: "+ "id:"+plan.getId()+"; propietario:"+plan.getCreator().getUsername()+"; nombre:"+plan.getName()+
                "; fecha: "+plan.dateToString()+ "; lugar: "+plan.getMeetPlace()+"; aforo:"+plan.getCapacity()+"; duración:"+plan.getDuration()+
                " min; coste:"+plan.getCost()+ "€; \n"+plan.getActivityCollection().listActivities()+"\nparticipantes: ");
        plan.getUserCollection().listUsers();
        System.out.println();
    }
}

