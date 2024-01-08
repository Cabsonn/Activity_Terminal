public class CreateActivityCommand extends Command{
    private final int CAP_PARAMS = 6;
    private String[] params;
    public CreateActivityCommand(SocialManager socialManager,Prompt prompt){
        super("create-activity",6,socialManager,prompt);
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
        return this.expectedParameters - 1 == this.params.length || this.expectedParameters == this.params.length;
    }
    public ActivityType activityType(){
        return new ActivityType(this.params[0]);
    }
    public void execute(){
        Error error = this.socialManager.checkActivity();
        Activity activity;
        if(error.isNull()){
            if(this.params.length == this.CAP_PARAMS){
                activity = socialManager.createActivity(this.activityType(),this.params[1],this.params[2],
                        Integer.parseInt(this.params[3]),Integer.parseInt(this.params[4]),
                        Integer.parseInt(this.params[5]));
                this.showActivity(activity);
            }else{
                activity = socialManager.createActivityNoLimit(this.activityType(),this.params[1],this.params[2],
                        Integer.parseInt(this.params[3]),Integer.parseInt(this.params[4]));
                this.showActivityNoLimit(activity);
            }
        }else{
            error.writeln();
        }
    }
    public void showActivity(Activity activity){
        System.out.println("Actividad creada: id:"+activity.getId()+"; nombre: " + activity.getName() + "; descripción: " + activity.getDescription() + "; duración: "
                + activity.getDuration() + " min; coste: " + activity.getCost() + "€; aforo: " + activity.getCapacity());
    }
    public void showActivityNoLimit(Activity activity){
        System.out.println("Actividad creada: id:"+activity.getId()+"; nombre: " + activity.getName() + "; descripción: " + activity.getDescription() + "; duración: "
                + activity.getDuration() + " min; coste: " + activity.getCost() + "€; aforo: sin límite.");
    }
}
