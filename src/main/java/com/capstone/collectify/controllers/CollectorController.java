package com.capstone.collectify.controllers;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.services.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class CollectorController {

    @Autowired
    CollectorService collectorService;

    // Create User
    @RequestMapping(value = "/collector",method = RequestMethod.POST)
    public ResponseEntity<Object> createCollector(@RequestBody Collector collector){
        collectorService.createCollector(collector);
        return new ResponseEntity<>("Collector Account created Successfully", HttpStatus.CREATED);
    }
    //  Get all User
    @RequestMapping(value = "/collector" , method = RequestMethod.GET)
    public ResponseEntity<Object> getUsername() {
        return new ResponseEntity<>(collectorService.getUsername(), HttpStatus.OK);
    }
    // Delete a U
    @RequestMapping (value = "/collector/{collectorid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClient(@PathVariable Long collectorid){
        return collectorService.deleteCollector(collectorid);
    }
    // Update a post
    @RequestMapping (value = "/collector/{collectorid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCollector(@PathVariable Long collectorid, @RequestBody Collector collector){
        return collectorService.updateCollector(collectorid,collector);
    }
}
