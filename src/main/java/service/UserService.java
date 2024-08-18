package service;

import dao.UserDAO;
import lombok.RequiredArgsConstructor;
import model.User;
import model.dto.Credentials;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        try {
            return userDAO.getAll();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return new ArrayList<>();
    }

    public void addUser(User user) throws IOException {
        userDAO.add(user);
    }

    public Optional<User> getCheckedUser(Credentials credentials) {
        List<User> allUsers = getAllUsers();
        return allUsers.stream()
                .filter(login -> login.getLogin().equalsIgnoreCase(credentials.getLogin()))
                .filter(user -> passwordEncoder.matches(credentials.getPassword(), user.getPassword()))
                .findFirst();
    }

    public String containsLoginCheck(String login) {
        List<User> allUsers = getAllUsers();
        Optional<User> optionalUser = allUsers.stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login))
                .findFirst();
        if (optionalUser.isPresent()) {
            return "User with that login name already exists";
        } else 
            return null;
    }

    public String encodePassword(String inputPassword) {
        return passwordEncoder.encode(inputPassword);
    }
}
