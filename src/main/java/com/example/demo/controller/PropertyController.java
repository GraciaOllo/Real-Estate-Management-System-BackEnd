package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Property;
import com.example.demo.model.Tenant;
import com.example.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGENT', 'TENANT')")
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @PostMapping("/create-property")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {

        return ResponseEntity.ok(propertyService.createProperty(property));
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        return ResponseEntity.ok(propertyService.updateProperty(id, property));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok("Property deleted successfully!");
    }


    @GetMapping("/getPropertyById/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @GetMapping("/search/location")
    public ResponseEntity<List<Property>> searchByLocation(@RequestParam String location) {
        return ResponseEntity.ok(propertyService.searchByLocation(location));
    }

    @GetMapping("/search/price")
    public ResponseEntity<List<Property>> searchByPriceRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(propertyService.searchByPriceRange(minPrice, maxPrice));
    }

    @GetMapping("/search/type")
    public ResponseEntity<List<Property>> searchByType(@RequestParam String type) {
        return ResponseEntity.ok(propertyService.searchByType(type));
    }

    @PostMapping("/{id}/sell")
    public ResponseEntity<Property> sellProperty(@PathVariable Long id, @RequestBody Customer customer) {
        return ResponseEntity.ok(propertyService.sellProperty(id, customer));
    }

    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<Property> uploadPropertyImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
           return ResponseEntity.ok(propertyService.uploadPropertyImage(id, file));
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}