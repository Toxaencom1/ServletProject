package servlet;

import model.Advertisement;
import model.dto.AccountIdentifierDTO;
import service.AdService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/ad")
public class AdServletGetPost extends HttpServlet {

    private AdService adService;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = getServletContext();
        adService = (AdService) servletContext.getAttribute("adService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AccountIdentifierDTO userDTO = (AccountIdentifierDTO) session.getAttribute("user");

        List<Advertisement> advertisementList = adService.readAdvertisements();

        req.setAttribute("login",userDTO.getLogin());
        req.setAttribute("elements", advertisementList);
        req.getRequestDispatcher("/jsp/ads.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long lastId = 1L;
        String name = req.getParameter("name");
        String model = req.getParameter("model");
        String cost = req.getParameter("cost");
        String url = req.getParameter("url");

        List<Advertisement> advertisements = adService.readAdvertisements();
        if (!advertisements.isEmpty() && advertisements.size() > 1) {
            lastId = advertisements.get((advertisements.size() - 1)).getId();
        }
        HttpSession session = req.getSession();
        AccountIdentifierDTO userDTO = (AccountIdentifierDTO) session.getAttribute("user");
        advertisements.add(Advertisement.builder()
                .id(lastId + 1)
                .userId(userDTO.getId())
                .name(name)
                .model(model)
                .cost(Double.parseDouble(cost))
                .url(url)
                .build());
        adService.writeAdvertisements(advertisements);

        resp.setStatus(201);
        resp.setContentType("text/html; charset=UTF-8");
        resp.sendRedirect("/ad");
    }
}
