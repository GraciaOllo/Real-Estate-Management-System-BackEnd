package com.example.demo.service;

import com.example.demo.model.Complaint;
import com.example.demo.model.Property;
import com.example.demo.model.Tenant;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.TenantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final TenantRepository tenantRepository;
    private final PropertyRepository propertyRepository;

    public ComplaintService(ComplaintRepository complaintRepository, TenantRepository tenantRepository, PropertyRepository propertyRepository) {
        this.complaintRepository = complaintRepository;
        this.tenantRepository = tenantRepository;
        this.propertyRepository = propertyRepository;
    }

    @Transactional
    public Complaint createComplaint(Long tenantId, Long propertyId, String description) {
        Tenant tenant = tenantRepository.findById(Math.toIntExact(tenantId)).orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new IllegalArgumentException("Property not found"));

        Complaint complaint = new Complaint();
        complaint.setTenant(tenant);
        complaint.setProperty(property);
        complaint.setDescription(description);
        complaint.setDateCreated(LocalDateTime.now());
        complaint.setStatus(Complaint.ComplaintStatus.PENDING);

        return complaintRepository.save(complaint);
    }

    public List<Complaint> getComplaintsByTenant(Long tenantId) {
        return complaintRepository.findByTenantId(tenantId);
    }

    public List<Complaint> getComplaintsByProperty(Long propertyId) {
        return complaintRepository.findByPropertyId(propertyId);
    }

    @Transactional
    public Complaint resolveComplaint(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new IllegalArgumentException("Complaint not found"));
        complaint.setStatus(Complaint.ComplaintStatus.RESOLVED);
        return complaintRepository.save(complaint);
    }

    @Transactional
    public Complaint rejectComplaint(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new IllegalArgumentException("Complaint not found"));
        complaint.setStatus(Complaint.ComplaintStatus.REJECTED);
        return complaintRepository.save(complaint);
    }
}
