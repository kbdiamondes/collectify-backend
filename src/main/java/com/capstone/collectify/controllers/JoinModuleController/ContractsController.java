package com.capstone.collectify.controllers.JoinModuleController;

import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.models.ResellerModule.AssignCollectors;
import com.capstone.collectify.repositories.ResellerRepository;
import com.capstone.collectify.services.ResellerServices.AssignCollectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/resellerPage")
public class ContractsController {
    @Autowired
    AssignCollectorsService assignCollectorsService;
    
    @Autowired
    private ResellerRepository resellerRepository;

    @RequestMapping(value="/assignCollectors/{resellerId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createAssignCollectors(@PathVariable Long resellerId ,@RequestBody AssignCollectors assignCollectors) {
        Reseller reseller = resellerRepository.findById(resellerId).orElse(null);
        if (reseller != null) {
            assignCollectorsService.createAssignCollectors(resellerId,assignCollectors);
            return new ResponseEntity<>("Assign Collectors created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Assign Collectors Records does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/assignCollectors", method = RequestMethod.GET)
    public ResponseEntity<Object> getAssignCollectors() {
        return new ResponseEntity<>(assignCollectorsService.getAssignCollectors(), HttpStatus.OK);
    }

    @RequestMapping(value="/assignCollectors/reseller/{resellerId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAssignCollectorsByResellerId(@PathVariable Long resellerId) {
        return new ResponseEntity<>(assignCollectorsService.getAssignCollectorsByResellerId(resellerId), HttpStatus.OK);
    }

    @RequestMapping(value = "/assignCollectors/{resellerId}/{assignCollectors_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAssignCollectors(@PathVariable Long resellerId,@PathVariable Long assignCollectors_id) {
        return assignCollectorsService.deleteAssignCollectors(resellerId,assignCollectors_id);
    }
    @RequestMapping(value="/assignCollectors/{resellerId}/{assignCollectors_id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAssignCollectors(@PathVariable Long resellerId,@PathVariable Long assignCollectors_id, @RequestBody AssignCollectors assignCollectors) {
        return assignCollectorsService.updateAssignCollectors(resellerId,assignCollectors_id, assignCollectors);
    }
}
