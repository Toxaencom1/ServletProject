package servlet.filter;

import model.Advertisement;
import model.dto.AccountIdentifierDTO;
import model.dto.AdvertisementDTO;
import model.helper.AdFormDataExtractor;
import model.validator.Validator;
import model.validator.advertisement.DoubleValidator;
import model.validator.advertisement.StringValidator;
import model.validator.advertisement.UrlValidator;
import service.AdService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/ad")
public class AdValidateFilter implements Filter {
    private AdService adService;
    private Validator validator;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ServletContext servletContext = filterConfig.getServletContext();
        validator = (Validator) servletContext.getAttribute("validator");
        adService = (AdService) servletContext.getAttribute("adService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            AdvertisementDTO adData = AdFormDataExtractor.extract(req);
            validator.setValidateStrategy(new StringValidator());
            List<String> errors = new ArrayList<>(validator.validate(adData.getName(), "Name"));
            errors.addAll(validator.validate(adData.getModel(), "Model"));
            validator.setValidateStrategy(new DoubleValidator());
            errors.addAll(validator.validate(adData.getCost(), "Cost"));
            validator.setValidateStrategy(new UrlValidator());
            errors.addAll(validator.validate(adData.getUrl(), "URL"));
            List<Advertisement> advertisementList = adService.readAdvertisements();
            HttpSession session = req.getSession();
            AccountIdentifierDTO userDTO = (AccountIdentifierDTO) session.getAttribute("user");
            if (errors.isEmpty()) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                req.setAttribute("login", userDTO.getLogin());
                req.setAttribute("elements", advertisementList);
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/jsp/ads.jsp").forward(req, resp);
            }
        } else filterChain.doFilter(req, resp);
    }
}
