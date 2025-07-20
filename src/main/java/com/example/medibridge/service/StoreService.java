package com.example.medibridge.service;

import com.example.medibridge.model.Owner;
import com.example.medibridge.model.Store;
import com.example.medibridge.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AuthService authService;

    public boolean updateStoreDetails(int storeId, String storeName, String location) {
        Owner currentOwner = authService.getCurrentOwner();
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null || store.getOwner().getId() != currentOwner.getId()) {
            return false;
        }

        store.setStoreName(storeName);
        store.setLocation(location);
        storeRepository.save(store);
        return true;
    }

    public boolean removeStore(int storeId) {
        Owner currentOwner = authService.getCurrentOwner();
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null || store.getOwner().getId() != currentOwner.getId()) {
            return false;
        }

        storeRepository.delete(store);
        return true;
    }

    public List<Store> getStores() {
        Owner currentOwner = authService.getCurrentOwner();
        return currentOwner.getStores();
    }

    public String addStore(String storeName, String location) {
        Owner currentOwner = authService.getCurrentOwner();
        Store store = new Store();
        store.setStoreName(storeName);
        store.setLocation(location);
        store.setOwner(currentOwner);
        storeRepository.save(store);
        return "Successfully added";
    }
}
