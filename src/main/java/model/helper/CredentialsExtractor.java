package model.helper;

import model.dto.Credentials;

import javax.servlet.http.HttpServletRequest;

public class CredentialsExtractor {
    public static Credentials extract(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        return new Credentials(login, password);
    }
}
