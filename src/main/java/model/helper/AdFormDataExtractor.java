package model.helper;

import model.dto.AdvertisementDTO;

import javax.servlet.http.HttpServletRequest;

public class AdFormDataExtractor {
    public static AdvertisementDTO extract(HttpServletRequest request) {
        String name = request.getParameter("name");
        String model = request.getParameter("model");
        String cost = request.getParameter("cost");
        String url = request.getParameter("url");
        return new AdvertisementDTO(name, model, cost, url);
    }
}
