package GPS;

import GPS.Commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private final List<Command> commands;
    private String text;
    private final Scanner console;
    private final Prompt prompt;
    public CLI(SocialManager socialManager) {
        this.prompt = new Prompt();
        this.commands = new ArrayList<>();
        this.commands.add(new CreateUserCommand(socialManager, this.prompt));
        this.commands.add(new LogInCommand(socialManager,this.prompt));
        this.commands.add(new LogOutCommand(socialManager,this.prompt));
        this.commands.add(new CreateActivityCommand(socialManager,this.prompt));
        this.commands.add(new CreatePlanCommand(socialManager,this.prompt));
        this.commands.add(new AddActivityPlanCommand(socialManager,this.prompt));
        this.commands.add(new ListPlansCommand(socialManager,this.prompt));
        this.commands.add(new JoinPlanCommand(socialManager,this.prompt));
        this.commands.add(new LeavePlanCommand(socialManager,this.prompt));
        this.commands.add(new ClosePlanCommand(socialManager, this.prompt));
        this.console = new Scanner(System.in);
        this.text = this.commandText();
    }

    public Command selectCommand() {
        for (Command command : this.commands) {
            if (command.parse(this.getText()) && command.checkParams(this.getText())) {
                return command;
            }
        }
        return null;
    }

    public String commandText() {
        System.out.print(this.prompt.getPath());
        this.text = console.nextLine();
        return this.text;
    }
    public void executeCommand() {
        try {
            this.selectCommand().execute();
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            Error.INVALID_COMMAND.writeln();
        }
    }
    public void start() {
        do {
            this.executeCommand();
            this.setText(this.commandText());
        } while (!this.getText().equals("exit"));
    }
    public String getText() {
        return this.text;
    }
    private void setText(String text){
        this.text = text;
    }
}

