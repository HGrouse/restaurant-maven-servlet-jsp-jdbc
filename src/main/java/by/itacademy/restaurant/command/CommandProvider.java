package by.itacademy.restaurant.command;

import by.itacademy.restaurant.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();

    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {

        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.EXIT, new LogOutCommand());
        commands.put(CommandName.CHANGE_LOCAL, new ChangeLocalCommand());

        commands.put(CommandName.GET_ALL_USERS, new GetAllUsersCommand());
        commands.put(CommandName.EDIT_PROFILE, new EditProfileCommand());
        commands.put(CommandName.DISMISS, new DismissCommand());

        commands.put(CommandName.TO_REGISTRATION_PAGE, new ToRegistrationPageCommand());
        commands.put(CommandName.TO_USER_INFORMATION_PAGE, new ToUserInformationPageCommand());
        commands.put(CommandName.TO_EDIT_PROFILE_PAGE, new ToEditProfileCommand());
        commands.put(CommandName.TO_ERROR_PAGE, new ToErrorPageCommand());
        commands.put(CommandName.TO_DISMISSAL_PAGE, new ToDismissalPageCommand());



    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName){

        CommandName name = null;
        Command command = null;

        try{
            name = CommandName.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e){
            name = CommandName.NO_SUCH_COMMAND;
        }
        command = commands.get(name);
        return command;
    }
}
