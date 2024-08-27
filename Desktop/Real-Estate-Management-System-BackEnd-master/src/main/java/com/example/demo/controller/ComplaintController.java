package com.example.demo.controller;

import com.example.demo.model.Complaint;
import com.example.demo.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/create")
    public ResponseEntity<Complaint> createComplaint(@RequestParam Long tenantId,
                                                     @RequestParam Long propertyId,
                                                     @RequestParam String description) {
        return ResponseEntity.ok(complaintService.createComplaint(tenantId, propertyId, description));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<Complaint>> getComplaintsByTenant(@PathVariable Long tenantId) {
        return ResponseEntity.ok(complaintService.getComplaintsByTenant(tenantId));
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<Complaint>> getComplaintsByProperty(@PathVariable Long propertyId) {
        return ResponseEntity.ok(complaintService.getComplaintsByProperty(propertyId));
    }

    @PostMapping("/{complaintId}/resolve")
    public ResponseEntity<Complaint> resolveComplaint(@PathVariable Long complaintId) {
        return ResponseEntity.ok(complaintService.resolveComplaint(complaintId));
    }

    @PostMapping("/{complaintId}/reject")
    public ResponseEntity<Complaint> rejectComplaint(@PathVariable Long complaintId) {
        return ResponseEntity.ok(complaintService.rejectComplaint(complaintId));
    }
}
