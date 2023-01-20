package com.cargo.controller.command;

import com.cargo.controller.command.PageCommands.*;
import com.cargo.controller.command.ManagerCommands.ManagerActions.EditCargoCommand;
import com.cargo.controller.command.UserCommands.MakeCargoCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("register", new RegisterCommand());
        commands.put("registerpage", new RegisterPageCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("loginpage", new LoginPageCommand());
        commands.put("showcargospage", new ShowCargosPageCommand());
        commands.put("makeCargo", new MakeCargoCommand());
        commands.put("makecargopage", new MakeCargoPageCommand());
        commands.put("showmanagerpage", new ShowManagerPageCommand());
        commands.put("calculate", new CalculateCommand());
        commands.put("calculatorpage", new CalculatePageCommand());
        commands.put("changeprofile", new EditProfileCommand());
        commands.put("changeprofilepage", new EditProfilePageCommand());
        commands.put("editcargo", new EditCargoCommand());
        commands.put("editcargopage", new EditCargoPageCommand());
        commands.put("SearchCityGuest",new SearchCityGuestCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());
    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
