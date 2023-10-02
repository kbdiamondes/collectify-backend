package com.capstone.collectify.services.ResellerServices;

import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.models.ResellerModule.MyCollectors;
import com.capstone.collectify.repositories.ResellerRepositories.MyCollectorsRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MyCollectorsServiceImpl implements MyCollectorsService {
    @Autowired
    private MyCollectorsRepository myCollectorsRepository;

    @Autowired
    private ResellerRepository resellerRepository;

    // Create My Collectors
    public void createMyCollectors(Long resellerId, MyCollectors myCollectors){
        Reseller reseller = resellerRepository.findById(resellerId).orElse(null);
        if(reseller != null){
            myCollectors.setReseller(reseller);
            myCollectorsRepository.save(myCollectors);
        }
    }
    // Get all Collector
    public Iterable<MyCollectors> getMyCollectors(){
        return myCollectorsRepository.findAll();
    }

    public Iterable<MyCollectors> getMyCollectorsByResellerId(Long resellerId){
        return myCollectorsRepository.findMyCollectorsByResellerId(resellerId);
    }


    // delete Collectors

    public ResponseEntity deleteMyCollectors(Long resellerId, Long id){
        MyCollectors myCollectorsToDelete = myCollectorsRepository.findById(id).orElse(null);
        if (myCollectorsToDelete != null && myCollectorsToDelete.getReseller().getReseller_id().equals(resellerId)) {
            myCollectorsRepository.deleteById(id);
            return new ResponseEntity<>("My Collectors Deleted Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("My Collectors Not Found",HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity updateMyCollectors(Long resellerId, Long id, MyCollectors myCollectors){
        MyCollectors myCollectorsForUpdate = myCollectorsRepository.findById(id).orElse(null);
        if (myCollectorsForUpdate != null && myCollectorsForUpdate.getReseller().getReseller_id().equals(resellerId)){
            myCollectorsForUpdate.setCollectionStatus(myCollectors.getCollectionStatus());
            myCollectorsForUpdate.setRequiredCollectibles(myCollectors.getRequiredCollectibles());
            myCollectorsRepository.save(myCollectorsForUpdate);
            return new ResponseEntity("My Collectors updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity("My Collectors updated successfully", HttpStatus.NOT_FOUND);
    }
}
