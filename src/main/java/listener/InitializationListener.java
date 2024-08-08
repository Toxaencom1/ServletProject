package listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dao.AdDao;
import service.AdService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        AdDao adDao = new AdDao(mapper);
        AdService service = new AdService(adDao);
        servletContext.setAttribute("service", service);
    }
}
