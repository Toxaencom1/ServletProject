package servlet;

import model.Advertisement;
import service.AdService;
import template.HtmlGenerator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ad")
public class AdServletGetPost extends HttpServlet {

    private AdService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = getServletContext();
        service = (AdService) servletContext.getAttribute("service");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlPage = HtmlGenerator.generateHtml(service.readAdvertisements());
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(htmlPage);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long lastId = 1L;
        String name = req.getParameter("name");
        String model = req.getParameter("model");
        String cost = req.getParameter("cost");
        String url = req.getParameter("url");

        List<Advertisement> advertisements = service.readAdvertisements();
        if (!advertisements.isEmpty() && advertisements.size() > 1) {
            lastId = advertisements.get((advertisements.size() - 1)).getId();
        }
        advertisements.add(Advertisement.builder()
                .id(lastId + 1)
                .name(name)
                .model(model)
                .cost(Double.parseDouble(cost))
                .url(url)
                .build());
        service.writeAdvertisements(advertisements);

        resp.setStatus(201);
        resp.setContentType("text/html; charset=UTF-8");
        resp.sendRedirect("/ad");
    }
}
