package com.capstone.collectify.controllers.ResellersControllers;

import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.models.ResellerModule.SoldItems;
import com.capstone.collectify.repositories.ResellerRepository;
import com.capstone.collectify.services.ResellerServices.SoldItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


    @RestController
    @CrossOrigin
    @RequestMapping("/resellerPage")
    public class SoldItemsController {
        @Autowired
        SoldItemsService soldItemsService;

        @Autowired
        private ResellerRepository resellerRepository;

        @RequestMapping(value="/soldItems/{resellerId}", method = RequestMethod.POST)
        public ResponseEntity<Object> createSoldItems(@PathVariable Long resellerId,@RequestBody SoldItems soldItems) {
            Reseller reseller = resellerRepository.findById(resellerId).orElse(null);
            if (reseller != null) {
                soldItemsService.createSoldItems(resellerId,soldItems);
                return new ResponseEntity<>("Sold Items created successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Sold Items Records does not exist", HttpStatus.NOT_FOUND);
            }
        }
        @RequestMapping(value="/soldItems", method = RequestMethod.GET)
        public ResponseEntity<Object> getSoldItems() {
            return new ResponseEntity<>(soldItemsService.getSoldItems(), HttpStatus.OK);
        }

        @RequestMapping(value="/soldItems/reseller/{resellerId}", method = RequestMethod.GET)
        public ResponseEntity<Object> getSoldItemsByResellerId(@PathVariable Long resellerId) {
            return new ResponseEntity<>(soldItemsService.getSoldItemsByResellerId(resellerId), HttpStatus.OK);
        }

        @RequestMapping(value = "/soldItems/{resellerId}/{soldItems_id}", method = RequestMethod.DELETE)
        public ResponseEntity<Object> deleteSoldItems(@PathVariable Long resellerId,@PathVariable Long soldItems_id) {
            return soldItemsService.deleteSoldItems(resellerId,soldItems_id);
        }

        @RequestMapping(value="/soldItems/{resellerId}/{soldItems_id}", method = RequestMethod.PUT)
        public ResponseEntity<Object> updateSoldItems(@PathVariable Long resellerId,@PathVariable Long soldItems_id, @RequestBody SoldItems soldItems) {
            return soldItemsService.updateSoldItems(resellerId,soldItems_id, soldItems);
        }
    }

