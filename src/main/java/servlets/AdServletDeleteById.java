package servlets;

import lombok.SneakyThrows;
import service.AdService;
import template.HtmlGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ad/delete/*")
public class AdServletDeleteById extends HttpServlet {
    private AdService service;

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        service = AdService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        Long itemId = Long.parseLong(pathInfo.substring(1));

        service.deleteAdvertisementsById(itemId);

        String htmlPage = HtmlGenerator.generateHtml(service.readAdvertisements());
        resp.setContentType("text/html");
        resp.getWriter().write(htmlPage);
    }
}
