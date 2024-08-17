package servlet.advertisementsServlet;

import model.Advertisement;
import model.dto.AccountIdentifierDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ad")
public class AdServletCreate extends BaseAdServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AccountIdentifierDTO userDTO = (AccountIdentifierDTO) session.getAttribute("user");

        adService.addAdvertisement(Advertisement.builder()
                .id(adService.lastId() + 1)
                .userId(userDTO.getId())
                .name(req.getParameter("name"))
                .model(req.getParameter("model"))
                .cost(Double.parseDouble(req.getParameter("cost")))
                .url(req.getParameter("url"))
                .build());

        resp.setStatus(201);
        resp.setContentType("text/html; charset=UTF-8");
        resp.sendRedirect("/ad");
    }
}
