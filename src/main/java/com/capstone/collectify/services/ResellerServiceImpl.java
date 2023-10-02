package com.capstone.collectify.services;

import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.repositories.ResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResellerServiceImpl implements ResellerService {
    @Autowired
    private ResellerRepository resellerRepository;
    // Find reseller  by reseller name
    // Create reseller
    public void createReseller(Reseller reseller){
        resellerRepository.save(reseller);
    }

    // Get all reseller
    public Iterable<Reseller> getUsername(){
        return resellerRepository.findAll();
    }

    // Delete reseller
    public ResponseEntity deleteReseller(Long id){
        resellerRepository.deleteById(id);
        return new ResponseEntity<>("User Deleted successfully", HttpStatus.OK);
    }

    // Update a reseller
    public ResponseEntity updateReseller(Long id , Reseller reseller){
        // Find the reseller  to update
        Reseller resellerForUpdate = resellerRepository.findById(id).get();
        // Updating the reseller name and password
        resellerForUpdate.setFullName(reseller.getFullName());
        resellerForUpdate.setAddress(reseller.getAddress());
        // Saving and Updating a reseller
        resellerRepository.save(resellerForUpdate);
        return new ResponseEntity<>("User updated Successfully",HttpStatus.OK);
    }
}
