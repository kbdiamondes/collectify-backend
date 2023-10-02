package com.capstone.collectify.services;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.repositories.CollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CollectorServiceImpl implements CollectorService{
    @Autowired
    private CollectorRepository collectorRepository;

    // Create a Collector
    public void createCollector(Collector collector){
        collectorRepository.save(collector);
    }
    // Get all Collector
    public Iterable<Collector> getUsername(){
        return collectorRepository.findAll();
    }
    // Delete Collector

    public ResponseEntity deleteCollector(Long id){
        collectorRepository.deleteById(id);
        return new ResponseEntity<>("Collector Deleted successfully", HttpStatus.OK);
    }
    public ResponseEntity updateCollector(Long id, Collector collector){
        // Find the user to update
        Collector collectorForUpdate = collectorRepository.findById(id).get();
        // Updating the username and password
        collectorForUpdate .setFullName(collector.getFullName());
        collectorForUpdate .setAddress(collector.getAddress());
        // Saving and Updating a user
        collectorRepository.save(collectorForUpdate );
        return new ResponseEntity<>("Collector updated Successfully",HttpStatus.OK);
    }
}
