package com.capstone.collectify.services.ResellerServices;

import com.capstone.collectify.models.ResellerModule.AssignCollectors;
import org.springframework.http.ResponseEntity;

public interface AssignCollectorsService{
    void createAssignCollectors(Long resellerId,AssignCollectors assignCollectors);

    Iterable<AssignCollectors> getAssignCollectors();

    Iterable<AssignCollectors>getAssignCollectorsByResellerId(Long resellerId);

    ResponseEntity deleteAssignCollectors(Long resellerId,Long id);

    ResponseEntity updateAssignCollectors(Long resellerId ,Long id, AssignCollectors assignCollectors);
}