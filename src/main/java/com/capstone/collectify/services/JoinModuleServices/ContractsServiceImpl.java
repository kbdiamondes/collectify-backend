package com.capstone.collectify.services.JoinModuleServices;

import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.models.ResellerModule.AssignCollectors;
import com.capstone.collectify.repositories.ResellerRepositories.AssignCollectorsRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContractsServiceImpl implements ContractsService {
    @Autowired
    private AssignCollectorsRepository assignCollectorsRepository;

    @Autowired
    private ResellerRepository resellerRepository;

    // create Assign Collectors
    public void createAssignCollectors(Long resellerId, AssignCollectors assignCollectors){
        Reseller reseller = resellerRepository.findById(resellerId).orElse(null);
        if(reseller != null){
            assignCollectors.setReseller(reseller);
            assignCollectorsRepository.save(assignCollectors);
        }
    }
    // get all Assign Collectors
    public Iterable<AssignCollectors> getAssignCollectors(){
        return assignCollectorsRepository.findAll();
    }

    public Iterable<AssignCollectors> getAssignCollectorsByResellerId(Long resellerId){
        return assignCollectorsRepository.findAssignCollectorsByResellerId(resellerId);
    }

    // delete Assign Collectors
    public ResponseEntity deleteAssignCollectors(Long resellerId,Long id){
       AssignCollectors assignCollectorsToDelete = assignCollectorsRepository.findById(id).orElse(null);
       if (assignCollectorsToDelete != null && assignCollectorsToDelete.getReseller().getReseller_id().equals(resellerId)) {
           assignCollectorsRepository.deleteById(id);
           return new ResponseEntity<>("Assign Collector Deleted Successfully", HttpStatus.OK);
       }else {
           return new ResponseEntity<>("Assign Collector Not Found",HttpStatus.NOT_FOUND);
       }
    }
    // update Assign Collectors
    public ResponseEntity updateAssignCollectors(Long resellerId,Long id, AssignCollectors assignCollectors){
        AssignCollectors assignCollectorsForUpdate = assignCollectorsRepository.findById(id).orElse(null);
        if(assignCollectorsForUpdate !=null && assignCollectorsForUpdate.getReseller().getReseller_id().equals(resellerId)){
            assignCollectorsForUpdate.setCollector(assignCollectors.getCollector());
            assignCollectorsRepository.save(assignCollectorsForUpdate);
            return new ResponseEntity("AssignCollectors updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity("AssignCollectors Not Found", HttpStatus.NOT_FOUND);
    }
}