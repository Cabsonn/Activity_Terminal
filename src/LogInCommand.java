public class LogInCommand extends Command {
    private String[] params;
    public LogInCommand(SocialManager socialManager, Prompt prompt) {
        super("login", 2,socialManager, prompt);
    }

    public boolean parse(String command) {
        String commandName = this.getCommandParts(command)[0];
        return commandName.equals(name);
    }
    private String[] getCommandParts(String command){
        return command.split(":",2);
    }
    public boolean checkParams (String command){
        String arguments = this.getCommandParts(command)[1];
        this.params = arguments.split(";");
        return (params.length == expectedParameters)
                && socialManager.findUser(this.params[0], this.params[1]) != null;
    }

    public void execute(){
        Error error = socialManager.login(this.params[0], this.params[1]);
        if(error.isNull()){
            this.updatePath();
        }else{
            error.writeln();
        }
    }
    public void updatePath(){
        this.prompt.changePath("gps-"+this.params[0]+"> ");
    }

}