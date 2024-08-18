package listener;

import config.AppConfig;

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

        servletContext.setAttribute("validator", appConfig.createValidator());
        servletContext.setAttribute("adService", appConfig.createAdService());
        servletContext.setAttribute("userService", appConfig.createUserService());
    }
}
