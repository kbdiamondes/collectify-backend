package com.capstone.collectify.services.eventlisteners;

import com.capstone.collectify.services.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServerStartupHandler {

    @Autowired
    private ContractServiceImpl contractService;

    private boolean hasFetchedData = false;

    @PostConstruct
    public void fetchDataOnStartup() {
        if (!hasFetchedData) {
            contractService.fetchDataAndSaveToDatabase();
            hasFetchedData = true;
        }
    }
}