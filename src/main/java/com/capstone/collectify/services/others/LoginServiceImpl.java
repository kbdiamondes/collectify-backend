package com.capstone.collectify.services.others;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject a PasswordEncoder bean

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

    @Override
    public Map<String, Object> authenticateUser(String username, String password) {
        Map<String, Object> result = new HashMap<>();

        // Search for the username in each entity repository
        Client client = clientRepository.findByUsername(username).orElse(null);
        Collector collector = (Collector) collectorRepository.findByUsername(username).orElse(null);
        Reseller reseller = resellerRepository.findByUsername(username).orElse(null);

        if (client != null && passwordEncoder.matches(password, client.getPassword())) {
            result.put("entityId", client.getClient_id());
            result.put("tableName", "Client");
            // Add other client-related information if needed
        } else if (collector != null && passwordEncoder.matches(password, collector.getPassword())) {
            result.put("entityId", collector.getCollector_id());
            result.put("tableName", "Collector");
            // Add other collector-related information if needed
        } else if (reseller != null && passwordEncoder.matches(password, reseller.getPassword())) {
            result.put("entityId", reseller.getReseller_id());
            result.put("tableName", "Reseller");
            // Add other reseller-related information if needed
        } else {
            result.put("entityId", null);
            result.put("tableName", "Not Found");
        }

        return result;
    }
}
