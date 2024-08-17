package service;

import dao.AdDAO;
import lombok.RequiredArgsConstructor;
import model.Advertisement;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AdService {

    private final AdDAO adDao;

    public List<Advertisement> readAdvertisements() {
        try {
            return adDao.getAll();
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return new ArrayList<>();
        }
    }

    public void addAdvertisement(Advertisement advertisement) {
        try {
            adDao.add(advertisement);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void deleteAdvertisementById(Long id) {
        try {
            adDao.deleteById(id);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public Long lastId(){
        Long lastId = 1L;
        try {
            List<Advertisement> advertisements = adDao.getAll();
            if (!advertisements.isEmpty() && advertisements.size() > 1) {
                return advertisements.get((advertisements.size() - 1)).getId();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return lastId;
    }
}
