package service;

import dao.AdDao;
import lombok.RequiredArgsConstructor;
import model.Advertisement;

import java.io.IOException;

import java.util.List;

@RequiredArgsConstructor
public class AdService {

    private final AdDao adDao;


    public List<Advertisement> readAdvertisements() throws IOException {
        return adDao.readAdvertisements();
    }

    public void writeAdvertisements(List<Advertisement> advertisements) {
        try {
            adDao.writeAdvertisements(advertisements);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void deleteAdvertisementsById(Long id) {
        try {
            adDao.deleteAdvertisementsById(id);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
