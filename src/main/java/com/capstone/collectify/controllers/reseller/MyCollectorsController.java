package com.capstone.collectify.controllers.reseller;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.services.reseller.MyCollectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/my-collectors")
public class MyCollectorsController {

    @Autowired
    private MyCollectorsService myCollectorsService;


    @GetMapping("/assigned/{resellerId}")
    public ResponseEntity<Map<String, List<?>>> getCollectorsAndContractsAssignedByReseller(@PathVariable Long resellerId) {
        Map<String, List<?>> response = new HashMap<>();
        List<Collector> collectors = myCollectorsService.getCollectorsAssignedByReseller(resellerId);
        response.put("collectors", collectors);

        if (!collectors.isEmpty()) {
            // Retrieve contracts for the first collector as an example; you can adjust this part as needed
            Collector firstCollector = collectors.get(0);
            List<Contract> contracts = myCollectorsService.getContractsAssignedToCollectorByReseller(resellerId, firstCollector.getCollector_id());
            response.put("contracts", contracts);
        }

        return ResponseEntity.ok(response);
    }

}
