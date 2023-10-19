package com.capstone.collectify.services.others;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CollectorRepository collectorRepository;

    @Autowired
    private ResellerRepository resellerRepository;

    @Override
    public Map<String, Object> findEntityInfoByUsername(String username){
        Map<String, Object> result = new HashMap<>();

        // Search for the username in each entity repository
        Client client = (Client) clientRepository.findByUsername(username).orElse(null);
        Collector collector = (Collector) collectorRepository.findByUsername(username).orElse(null);
        Reseller reseller = (Reseller) resellerRepository.findByUsername(username).orElse(null);

        if (client != null) {
            result.put("entityId", client.getClient_id());
            result.put("tableName", "Client");
        } else if (collector != null) {
            result.put("entityId", collector.getCollector_id());
            result.put("tableName", "Collector");
        } else if (reseller != null) {
            result.put("entityId", reseller.getReseller_id());
            result.put("tableName", "Reseller");
        } else {
            result.put("entityId", null);
            result.put("tableName", "Not Found");
        }

        return result;
    }
}
