package com.example.demo.service;


import com.example.demo.model.Property;
import com.example.demo.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private  PropertyRepository propertyRepository;


    public  List<Property> getAllProperties() {return propertyRepository.findAll();
    }

    public Property getPropertyById(Integer property_id) {
        return propertyRepository.findById(property_id).orElse(null);
    }

    public Property saveProperty(Property property) {
        propertyRepository.save(property);
        return property;
    }

    public void deleteProperty(Integer property_id) {
        propertyRepository.deleteById(property_id);
    }

}