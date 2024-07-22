package com.example.demo.controller;

import com.example.demo.model.Property;
import com.example.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/getAllProperties")
    public  List<Property> getAllProperties(){
        return propertyService.getAllProperties();
    }
    @GetMapping("/get-property/{property_id}")
    public Property getPropertyById(@PathVariable Integer property_id) {

        return propertyService.getPropertyById(property_id);
    }

    @PostMapping("/saveProperty")
    public void createProperty(@RequestBody Property property) {
        propertyService.saveProperty(property);
    }

    @DeleteMapping("/deleteProperty/{property_id}")
    public void deleteProperty(@PathVariable Integer property_id) {
        propertyService.deleteProperty(property_id);
    }

}
