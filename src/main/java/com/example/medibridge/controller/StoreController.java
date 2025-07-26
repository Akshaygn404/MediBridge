package com.example.medibridge.controller;

import com.example.medibridge.model.Store;
import com.example.medibridge.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/stores")
    public List<Store> fetchStores() {
        return storeService.getStores();
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/dashboard")
    public ResponseEntity<?> ownerDashboard() {
        return ResponseEntity.ok("Welcome Owner");
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/stores/addstores")
    public String insertStore(@RequestParam String store_name, @RequestParam String location) {
        return storeService.addStore(store_name, location);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/stores/{storeId}/update")
    public String updateStore(@PathVariable int storeId,
                              @RequestParam String store_name,
                              @RequestParam String location) {
        boolean success = storeService.updateStoreDetails(storeId, store_name, location);
        return success ? "Updated successfully" : "Invalid store details";
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/stores/{storeId}/delete")
    public String deleteStore(@PathVariable int storeId) {
        boolean success = storeService.removeStore(storeId);
        return success ? "Deleted Successfully" : "Deletion Failed";
    }
}
