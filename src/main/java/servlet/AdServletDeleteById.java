package servlet;

import service.AdService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ad/delete/*")
public class AdServletDeleteById extends HttpServlet {

    private AdService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = getServletContext();
        service = (AdService) servletContext.getAttribute("adService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/ad");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        Long itemId = Long.parseLong(pathInfo.substring(1));

        service.deleteAdvertisementById(itemId);

        resp.sendRedirect("/ad");
    }
}
