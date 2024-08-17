package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Advertisement;

import java.io.File;
import java.util.List;


public class AdDAO extends BaseDAO<Advertisement, Long> {

    public AdDAO(File file, ObjectMapper mapper) {
        super(file, mapper, new TypeReference<List<Advertisement>>() {
        });
    }

    @Override
    protected Long getId(Advertisement advertisement) {
        return advertisement.getId();
    }
}
