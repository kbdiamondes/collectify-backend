package com.capstone.collectify.controllers;

import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.services.ResellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class ResellerController {
    @Autowired

    ResellerService resellerService;

    // Create User
    @RequestMapping(value = "/reseller",method = RequestMethod.POST)
    public ResponseEntity<Object> createCollector(@RequestBody Reseller reseller){
        resellerService.createReseller(reseller);
        return new ResponseEntity<>("Reseller Account created Successfully", HttpStatus.CREATED);
    }
    //  Get all User
    @RequestMapping(value = "/reseller" , method = RequestMethod.GET)
    public ResponseEntity<Object> getUsername() {
        return new ResponseEntity<>(resellerService.getUsername(), HttpStatus.OK);
    }
    // Delete a U
    @RequestMapping (value = "/reseller/{resellerid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClient(@PathVariable Long resellerid){
        return resellerService.deleteReseller(resellerid);
    }
    // Update a post
    @RequestMapping (value = "/resel/{resellerid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCollector(@PathVariable Long resellerid, @RequestBody Reseller reseller){
        return resellerService.updateReseller(resellerid,reseller);
    }
}
