package com.capstone.collectify.controllers.ResellersControllers;

import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.models.ResellerModule.SendCollectors;
import com.capstone.collectify.repositories.ResellerRepository;
import com.capstone.collectify.services.ResellerServices.SendCollectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @CrossOrigin
    @RequestMapping("/resellerPage")
    public class SendCollectorsController {
        @Autowired
        SendCollectorsService sendCollectorsService;

        @Autowired
        private ResellerRepository resellerRepository;


        @RequestMapping(value="/sendCollectors/{resellerId}", method = RequestMethod.POST)
        public ResponseEntity<Object> createSendCollectors(@PathVariable Long resellerId,@RequestBody SendCollectors sendCollectors) {
            Reseller reseller = resellerRepository.findById(resellerId).orElse(null);
            if (reseller != null) {
                sendCollectorsService.createSendCollectors(resellerId,sendCollectors);
                return new ResponseEntity<>("SendCollectors created successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Assign Collectors Records does not exist", HttpStatus.NOT_FOUND);
            }
        }
        @RequestMapping(value="/sendCollectors", method = RequestMethod.GET)
        public ResponseEntity<Object> getSendCollectors() {
            return new ResponseEntity<>(sendCollectorsService.getSendCollectors(), HttpStatus.OK);
        }
        @RequestMapping(value="/sendCollectors/reseller/{resellerId}", method = RequestMethod.GET)
        public ResponseEntity<Object> getSendCollectorsByResellerId(@PathVariable Long resellerId) {
            return new ResponseEntity<>(sendCollectorsService.getSendCollectorsByResellerId(resellerId), HttpStatus.OK);
        }

        @RequestMapping(value = "/sendCollectors/{resellerId}/{sendCollectors_id}", method = RequestMethod.DELETE)
        public ResponseEntity<Object> deleteSendCollectors(@PathVariable Long resllerId, @PathVariable Long sendCollectors_id) {
            return sendCollectorsService.deleteSendCollectors(sendCollectors_id,sendCollectors_id);
        }

        @RequestMapping(value="/sendCollectors/{resellerId}/{sendCollectors_id}", method = RequestMethod.PUT)
        public ResponseEntity<Object> updateSendCollectors(@PathVariable Long resellerId,@PathVariable Long sendCollectors_id, @RequestBody SendCollectors sendCollectors) {
            return sendCollectorsService.updateSendCollectors(resellerId,sendCollectors_id, sendCollectors);
        }
}
