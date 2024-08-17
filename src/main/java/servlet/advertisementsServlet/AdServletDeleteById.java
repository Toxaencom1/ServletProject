package servlet.advertisementsServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ad/delete/*")
public class AdServletDeleteById extends BaseAdServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        adService.deleteAdvertisementById(Long.parseLong(pathInfo.substring(1)));

        resp.setContentType("text/html; charset=UTF-8");
        resp.sendRedirect("/ad");
    }
}
