package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Konstantin", "Ovchinnikov", (byte) 35);
        userService.saveUser("Anastasia", "Gordievich", (byte) 37);
        userService.saveUser("Kirill", "Fedianov", (byte) 36);
        userService.saveUser("Dmitri", "Gavrusev", (byte) 50);
        userService.getAllUsers().forEach(h -> System.out.println(h.toString()));
        userService.getAllUsers().forEach(h -> userService.removeUserById(h.getId()));
        userService.dropUsersTable();
    }
}
