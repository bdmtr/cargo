package com.cargo.controller.command;

import com.cargo.controller.command.PageCommands.*;
import com.cargo.model.dao.BranchDao;
import com.cargo.model.dao.CargoDao;
import com.cargo.model.dao.UserDao;
import com.cargo.model.service.BranchService;
import com.cargo.model.service.CargoService;
import com.cargo.model.service.UserService;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("register", new RegisterCommand(new UserService(UserDao.getInstance())));
        commands.put("registerpage", new RegisterPageCommand());
        commands.put("login", new LoginCommand(new UserService(UserDao.getInstance())));
        commands.put("logout", new LogoutCommand());
        commands.put("loginpage", new LoginPageCommand());
        commands.put("showcargospage", new ShowUserPageCommand(new CargoService(CargoDao.getInstance()), new UserService(UserDao.getInstance())));
        commands.put("makeCargo", new MakeCargoCommand(new CargoService(CargoDao.getInstance()), new BranchService(BranchDao.getInstance()), new UserService(UserDao.getInstance())));
        commands.put("makecargopage", new MakeCargoPageCommand());
        commands.put("showmanagerpage", new ShowManagerPageCommand(new CargoService(CargoDao.getInstance())));
        commands.put("calculate", new CalculateCommand(new BranchService(BranchDao.getInstance())));
        commands.put("calculatorpage", new CalculatePageCommand());
        commands.put("changeprofile", new EditProfileCommand(new UserService(UserDao.getInstance())));
        commands.put("changeprofilepage", new EditProfilePageCommand());
        commands.put("editcargo", new EditCargoCommand(new CargoService(CargoDao.getInstance())));
        commands.put("editcargopage", new EditCargoPageCommand(new CargoService(CargoDao.getInstance())));
        commands.put("SearchCityGuest", new ShowGuestPageCommand(new CargoService(CargoDao.getInstance())));
        commands.put("changeLanguage", new ChangeLanguageCommand());
        commands.put("error", new ShowErrorPageCommand());
        commands.put("invoice", new CreateInvoiceCommand(new CargoService(CargoDao.getInstance()), new UserService(UserDao.getInstance()), new BranchService(BranchDao.getInstance())));
        commands.put("paypage", new PayPageCommand(new CargoService(CargoDao.getInstance()), new UserService(UserDao.getInstance())));
        commands.put("pay", new PayCommand(new CargoService(CargoDao.getInstance()), new UserService(UserDao.getInstance())));

    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
