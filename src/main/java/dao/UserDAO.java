package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;

import java.io.File;
import java.util.List;
import java.util.UUID;


public class UserDAO extends BaseDAO<User, UUID> {

    public UserDAO(File file, ObjectMapper mapper) {
        super(file, mapper, new TypeReference<List<User>>() {
        });
    }

    @Override
    protected UUID getId(User user) {
        return user.getId();
    }
}
