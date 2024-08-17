package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseDAO<TYPE, ID> {
    protected final File file;
    protected final ObjectMapper mapper;
    protected final TypeReference<List<TYPE>> typeReferenceList;

    public List<TYPE> getAll() throws IOException {
        return mapper.readValue(file, typeReferenceList);
    }

    public void add(TYPE typeOfDAO) throws IOException {
        List<TYPE> list = getAll();
        list.add(typeOfDAO);
        mapper.writeValue(file, list);
    }

    public void deleteById(ID id) throws IOException {
        List<TYPE> list = getAll();
        list.removeIf(typeOfDAO -> getId(typeOfDAO).equals(id));
        mapper.writeValue(file, list);
    }

    protected abstract ID getId(TYPE typeOfDAO);
}
