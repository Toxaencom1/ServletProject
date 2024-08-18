package config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dao.AdDAO;
import dao.UserDAO;
import model.validator.Validator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.AdService;
import service.UserService;

import java.io.File;

public class AppConfig {

    public ObjectMapper createYamlMapper() {
        return new ObjectMapper(new YAMLFactory());
    }

    public ObjectMapper createJsonMapper() {
        ObjectMapper jsonMapper = new ObjectMapper(new JsonFactory());
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return jsonMapper;
    }

    public Validator createValidator() {
        return new Validator();
    }

    public File getUserFile() {
        return new File("C:\\Users\\Toxae\\IdeaProjects\\servletTask\\src\\main\\resources\\json\\users.json");
    }

    public File getStorageFile() {
        return new File("C:\\Users\\Toxae\\IdeaProjects\\servletTask\\src\\main\\resources\\yaml\\storage.yaml");
    }

    public AdDAO createAdDao() {
        return new AdDAO(getStorageFile(), createYamlMapper());
    }

    public UserDAO createUserDao() {
        return new UserDAO(getUserFile(), createJsonMapper());
    }

    public AdService createAdService() {
        return new AdService(createAdDao());
    }

    public UserService createUserService() {
        return new UserService(createUserDao(), createPasswordEncoder());
    }

    public BCryptPasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

