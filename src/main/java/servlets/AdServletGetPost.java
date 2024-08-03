package servlets;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import model.Advertisement;
import template.HtmlGenerator;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@WebServlet("/ad")
public class AdServletGetPost extends HttpServlet {
    private static final Path STORAGE_PATH = Paths.get("C:\\Users\\Toxae\\IdeaProjects\\servletTask\\src\\main\\webapp\\yaml\\storage.yaml");
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    List<Advertisement> advertisements;

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        if (Files.notExists(STORAGE_PATH)) {
            Files.createFile(STORAGE_PATH);
        } else {
            advertisements = mapper.readValue(STORAGE_PATH.toFile(), new TypeReference<List<Advertisement>>() {
            });
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String htmlPage = HtmlGenerator.generateHtml(advertisements);
        resp.setContentType("text/html");
        resp.getWriter().write(htmlPage);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long lastId = 0L;
        String name = req.getParameter("name");
        String model = req.getParameter("model");
        String cost = req.getParameter("cost");
        String url = req.getParameter("url");

        advertisements = mapper.readValue(STORAGE_PATH.toFile(), new TypeReference<List<Advertisement>>() {
        });

        if (!advertisements.isEmpty()) {
            lastId = advertisements.get((advertisements.size() - 1)).getId();
        }
        advertisements.add(new Advertisement(lastId + 1, name, model, Double.parseDouble(cost), url));
        writeActual();

        String htmlPage = HtmlGenerator.generateHtml(advertisements);
        resp.setContentType("text/html");
        resp.getWriter().write(htmlPage);
    }

    private void writeActual() {
        try {
            mapper.writeValue(STORAGE_PATH.toFile(), advertisements);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
