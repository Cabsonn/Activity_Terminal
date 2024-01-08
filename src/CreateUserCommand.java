public class CreateUserCommand extends Command{
    private String[] params;
    public CreateUserCommand(SocialManager socialManager, Prompt prompt){
        super("create-user",4,socialManager,prompt);
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
        Error error = this.socialManager.check_In(this.params[0], Integer.parseInt(this.params[1]), this.params[2], this.params[3]);
        User user;
        if(error.isNull()){
            user = this.socialManager.addUser(this.params[0], Integer.parseInt(this.params[1]), this.params[2], this.params[3]);
            this.showUser(user);
        }else{
            error.writeln();
        }
    }
    public void showUser(User user){
        System.out.println("Usuario creado: id:" + user.getId() + "; " + "nombre: " + user.getUsername() +
                "; edad: " + user.getAge() + "; móvil: " + user.getPhone() + "; clave:" + user.getPassword());
    }

}

