package com.capstone.collectify.services.JoinModuleServices;

import com.capstone.collectify.models.ResellerModule.MyCollectors;
import org.springframework.http.ResponseEntity;

public interface AssignedCollectionService {
    void createMyCollectors(Long resellerId,MyCollectors myCollectors);

    Iterable<MyCollectors> getMyCollectors();

    Iterable<MyCollectors>getMyCollectorsByResellerId(Long resellerId);

    ResponseEntity deleteMyCollectors(Long resellerId,Long id);

    ResponseEntity updateMyCollectors(Long resellerId,Long id, MyCollectors myCollectors);
}
