package servlet;

import model.User;
import model.dto.AccountIdentifierDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = getServletContext();
        service = (UserService) servletContext.getAttribute("userService");
        passwordEncoder = (BCryptPasswordEncoder) servletContext.getAttribute("bCrypt");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputLogin = req.getParameter("login");
        String inputPassword = req.getParameter("password");

        List<User> allUsers = service.getAllUsers();
        Optional<User> identifiedUser = allUsers.stream()
                .filter(login -> login.getLogin().equalsIgnoreCase(inputLogin))
                .filter(user -> passwordEncoder.matches(inputPassword, user.getPassword()))
                .findFirst();


        HttpSession session = req.getSession();

        if (identifiedUser.isPresent()) {
            User user = identifiedUser.get();
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
