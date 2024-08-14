package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
public class UserDAO {
    private static final Path STORAGE_PATH = Paths.get("C:\\Users\\Toxae\\IdeaProjects\\servletTask\\src\\main\\webapp\\json\\users.json");

    private final ObjectMapper mapper;

    public List<User> getAllUsers() throws IOException {
        return mapper.readValue(STORAGE_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public void addUser(User user) throws IOException {
        List<User> users = getAllUsers();
        users.add(user);
        mapper.writeValue(STORAGE_PATH.toFile(), users);
    }
}
