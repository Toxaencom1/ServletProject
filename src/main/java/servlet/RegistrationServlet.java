package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {

    private UserService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = getServletContext();
        service = (UserService) servletContext.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        service.addUser(User.builder()
                        .id(UUID.randomUUID())
                        .login(login)
                        .password(password)
                .build());

        resp.sendRedirect("/login");
    }
}
