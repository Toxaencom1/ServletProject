package service;

import dao.UserDAO;
import lombok.RequiredArgsConstructor;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserService {
    private final UserDAO userDAO;

    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return new ArrayList<>();
    }

    public void addUser(User user) throws IOException {
        userDAO.addUser(user);
    }
}
