package servlet.authServlet;

import model.User;
import model.dto.AccountIdentifierDTO;
import model.helper.CredentialsExtractor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


@WebServlet(urlPatterns = "/login",
        initParams = {
                @WebInitParam(name = "urlName", value = "/jsp/login.jsp")
        })
public class LoginServlet extends BaseAuthServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> checkedUser = userService.getCheckedUser(CredentialsExtractor.extract(req));

        HttpSession session = req.getSession();
        resp.setContentType("text/html; charset=UTF-8");

        if (checkedUser.isPresent()) {
            User user = checkedUser.get();
            session.setAttribute("user", AccountIdentifierDTO.builder()
                    .id(user.getId())
                    .login(user.getLogin())
                    .build());
            resp.sendRedirect("/ad");
        } else {
            resp.sendRedirect("/login");
        }
    }
}
