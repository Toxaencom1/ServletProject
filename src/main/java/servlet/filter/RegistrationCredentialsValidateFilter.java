package servlet.filter;

import model.dto.Credentials;
import model.helper.CredentialsExtractor;
import model.validator.Validator;
import model.validator.auth.LoginValidator;
import model.validator.auth.PasswordValidator;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/reg")
public class RegistrationCredentialsValidateFilter implements Filter {
    private UserService userService;
    private Validator validator;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ServletContext servletContext = filterConfig.getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
        validator = (Validator) servletContext.getAttribute("validator");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            Credentials credentials = CredentialsExtractor.extract((HttpServletRequest) servletRequest);
            validator.setValidateStrategy(new LoginValidator());
            List<String> errors = new ArrayList<>(validator.validate(credentials.getLogin(), "Login"));

            validator.setValidateStrategy(new PasswordValidator());
            errors.addAll(validator.validate(credentials.getPassword(), "Password"));
            String checkedResult = userService.containsLoginCheck(credentials.getLogin());
            if (checkedResult != null) {
                errors.add(checkedResult);
            }
            if (errors.isEmpty()) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
            }
        } else filterChain.doFilter(req, resp);
    }
}
