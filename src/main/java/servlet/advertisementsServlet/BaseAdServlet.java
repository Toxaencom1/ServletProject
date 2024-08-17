package servlet.advertisementsServlet;

import model.Advertisement;
import model.dto.AccountIdentifierDTO;
import service.AdService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public abstract class BaseAdServlet extends HttpServlet {

    protected AdService adService;

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
}
