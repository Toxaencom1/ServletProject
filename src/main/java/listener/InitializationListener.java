package listener;

import config.AppConfig;
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

        AppConfig appConfig = new AppConfig();

        AdService adService = appConfig.createAdService();
        UserService userService = appConfig.createUserService();

        servletContext.setAttribute("adService", adService);
        servletContext.setAttribute("userService", userService);
    }
}
