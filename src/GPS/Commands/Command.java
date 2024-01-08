package GPS.Commands;
import GPS.SocialManager;
import GPS.Prompt;

public abstract class Command {
    protected String name;
    protected int expectedParameters;

    protected SocialManager socialManager;
    protected Prompt prompt;

    public Command (String name, int expectedParameters, SocialManager socialManager, Prompt prompt){
        this.name = name;
        this.expectedParameters = expectedParameters;
        this.socialManager = socialManager;
        this.prompt = prompt;
    }

    public abstract boolean parse(String command);

    public abstract  boolean checkParams(String command);
    public abstract void execute();
}
