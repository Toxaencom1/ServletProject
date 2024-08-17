package servlet.authServlet;

import model.User;
import model.dto.Credentials;
import model.helper.CredentialsExtractor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/reg",
        initParams = {
                @WebInitParam(name = "urlName", value = "/jsp/registration.jsp")
        })
public class RegistrationServlet extends BaseAuthServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Credentials credentials = CredentialsExtractor.extract(req);

        String encodedPassword = userService.encodePassword(credentials.getPassword());
        userService.addUser(User.builder()
                .id(UUID.randomUUID())
                .login(credentials.getLogin())
                .password(encodedPassword)
                .build());

        resp.setContentType("text/html; charset=UTF-8");
        resp.sendRedirect("/login");
    }
}
