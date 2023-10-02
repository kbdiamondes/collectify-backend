package com.capstone.collectify.services.ResellerServices;

import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.models.ResellerModule.SoldItems;
import com.capstone.collectify.repositories.ResellerRepositories.SoldItemsRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SoldItemsServiceImpl implements SoldItemsService{
    @Autowired
    private SoldItemsRepository soldItemsRepository;
    
    @Autowired
    private ResellerRepository resellerRepository;

    
    public void createSoldItems(Long resellerId,SoldItems soldItems){
        Reseller reseller = resellerRepository.findById(resellerId).orElse(null);
        if(reseller != null){
            soldItems.setReseller(reseller);
            soldItemsRepository.save(soldItems);
        }
    }
    public Iterable<SoldItems> getSoldItems(){
        return soldItemsRepository.findAll();
    }


    public Iterable<SoldItems> getSoldItemsByResellerId(Long resellerId){
        return soldItemsRepository.findSoldItemsByResellerId(resellerId);
    }

    public ResponseEntity deleteSoldItems(Long resellerId,Long id){
        SoldItems soldItemToDelete = soldItemsRepository.findById(id).orElse(null);
        if (soldItemToDelete != null && soldItemToDelete.getReseller().getReseller_id().equals(resellerId)) {
            soldItemsRepository.deleteById(id);
            return new ResponseEntity<>("Sold Items Deleted Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Sold Items Not Found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateSoldItems(Long resellerId,Long id, SoldItems soldItems){
        SoldItems soldItemsForUpdate = soldItemsRepository.findById(id).orElse(null);
        if(soldItemsForUpdate !=null && soldItemsForUpdate.getReseller().getReseller_id().equals(resellerId)){
            soldItemsForUpdate.setItemName(soldItems.getItemName());
            soldItemsForUpdate.setStockQuantity(soldItems.getStockQuantity());
            return new ResponseEntity("Sold Items updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity("Sold Items Not Found", HttpStatus.NOT_FOUND);
    }
}

