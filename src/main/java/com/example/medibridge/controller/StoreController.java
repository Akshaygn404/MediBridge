package com.example.medibridge.controller;

import com.example.medibridge.model.Store;
import com.example.medibridge.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/stores")
    public List<Store> fetchStores() {
        return storeService.getStores();
    }

    @PostMapping("/stores/addstores")
    public String insertStore(@RequestParam String store_name, @RequestParam String location) {
        return storeService.addStore(store_name, location);
    }

    @PutMapping("/stores/{storeId}/update")
    public String updateStore(@PathVariable int storeId,
                              @RequestParam String store_name,
                              @RequestParam String location) {
        boolean success = storeService.updateStoreDetails(storeId, store_name, location);
        return success ? "Updated successfully" : "Invalid store details";
    }

    @DeleteMapping("/stores/{storeId}/delete")
    public String deleteStore(@PathVariable int storeId) {
        boolean success = storeService.removeStore(storeId);
        return success ? "Deleted Successfully" : "Deletion Failed";
    }
}
