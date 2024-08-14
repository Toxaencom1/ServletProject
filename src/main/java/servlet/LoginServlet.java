package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = getServletContext();
        service = (UserService) servletContext.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        List<User> allUsers = service.getAllUsers();
        Optional<User> identifiedUser = allUsers.stream()
                .filter(x -> x.getLogin().equals(login) && x.getPassword().equals(password))
                .findFirst();

        HttpSession session = req.getSession();

        if (identifiedUser.isPresent()) {
            session.setAttribute("userId", identifiedUser.get().getId());
            resp.sendRedirect("/ad");
        } else {
            resp.sendRedirect("/login");
        }
    }
}
