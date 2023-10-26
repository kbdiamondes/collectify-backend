package com.capstone.collectify.controllers.reseller;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.services.reseller.MyCollectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/my-collectors")
public class MyCollectorsController {

    @Autowired
    private MyCollectorsService myCollectorsService;


    @GetMapping("/assigned/{resellerId}")
    public ResponseEntity<List<Contract>> getAssignedContractsForReseller(@PathVariable Long resellerId) {
        List<Contract> assignedContracts = myCollectorsService.getAssignedContractsForReseller(resellerId);
        return ResponseEntity.ok(assignedContracts);
    }

}
