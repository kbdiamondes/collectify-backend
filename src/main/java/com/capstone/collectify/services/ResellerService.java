package com.capstone.collectify.services;

import com.capstone.collectify.models.Reseller;
import org.springframework.http.ResponseEntity;

public interface ResellerService {
    // Create Client
    void createReseller(Reseller reseller);
    // Get Client
    Iterable<Reseller> getUsername();
    // Delete Client
    ResponseEntity deleteReseller(Long id);
    // Update a Client
    ResponseEntity updateReseller(Long id, Reseller reseller);
}
