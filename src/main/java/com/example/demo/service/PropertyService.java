package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Property;
import com.example.demo.repository.PropertyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Property not found"));
        property.setName(propertyDetails.getName());
        property.setLocation(propertyDetails.getLocation());
        property.setPrice(propertyDetails.getPrice());
        property.setType(propertyDetails.getType());
        return propertyRepository.save(property);
    }


    public List<Property> searchByLocation(String location) {
        return propertyRepository.findByLocation(location);
    }

    public List<Property> searchByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return propertyRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Property> searchByType(String type) {
        return propertyRepository.findByType(type);
    }

    @Transactional
    public Property sellProperty(Long propertyId, Customer customer) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new IllegalArgumentException("Property not found"));

        if (property.getOwner() != null || property.getStatus() == Property.PropertyStatus.SOLD) {
            throw new IllegalStateException("Property already sold");
        }

        property.setOwner(customer);
        property.setStatus(Property.PropertyStatus.SOLD);
        return propertyRepository.save(property);
    }

    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Property not found"));
        propertyRepository.delete(property);
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Property not found"));
    }


    public Property uploadPropertyImage(Long propertyId, MultipartFile file) throws IOException {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new IllegalArgumentException("Property not found"));

        // Generate a unique filename
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = "images/" + filename;

        // Save the file locally
        File imageFile = new File(filePath);
        file.transferTo(imageFile);

        // Set the image URL in the property
        property.setImageUrl("/" + filePath);
        return propertyRepository.save(property);
    }

    @Transactional
    public Property rentProperty(Long propertyId, Customer customer) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new IllegalArgumentException("Property not found"));

        if (property.getStatus() == Property.PropertyStatus.SOLD) {
            throw new IllegalStateException("Cannot rent a sold property");
        }

        property.setOwner(customer);
        property.setStatus(Property.PropertyStatus.RENTED);
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {

        return List.of();
    }
}