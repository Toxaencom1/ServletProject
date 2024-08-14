package listener;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dao.AdDao;
import dao.UserDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.AdService;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ObjectMapper ymlMapper = new ObjectMapper(new YAMLFactory());
        ObjectMapper jsonMapper = new ObjectMapper(new JsonFactory());
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        AdDao adDao = new AdDao(ymlMapper);
        UserDAO userDao = new UserDAO(jsonMapper);
        AdService adService = new AdService(adDao);
        UserService userService = new UserService(userDao);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        servletContext.setAttribute("bCrypt", passwordEncoder);
        servletContext.setAttribute("adService", adService);
        servletContext.setAttribute("userService", userService);
    }
}
