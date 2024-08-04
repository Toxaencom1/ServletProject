package service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.Advertisement;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AdService {
    private static final Path STORAGE_PATH = Paths.get("C:\\Users\\Toxae\\IdeaProjects\\servletTask\\src\\main\\webapp\\yaml\\storage.yaml");

    private static AdService instance;
    ObjectMapper mapper;

    private AdService() {
        mapper = new ObjectMapper(new YAMLFactory());
    }

    public static AdService getInstance() {
        if (instance == null) {
            instance = new AdService();
        }
        return instance;
    }

    public List<Advertisement> readAdvertisements() throws IOException {
        return mapper.readValue(STORAGE_PATH.toFile(), new TypeReference<List<Advertisement>>() {
        });
    }

    public void writeAdvertisements(List<Advertisement> advertisements) throws IOException {
        mapper.writeValue(STORAGE_PATH.toFile(), advertisements);
    }

    public void deleteAdvertisementsById(Long id) throws IOException {
        List<Advertisement> advertisementList = readAdvertisements();
        advertisementList.removeIf(x -> x.getId().equals(id));
        writeAdvertisements(advertisementList);
    }
}
