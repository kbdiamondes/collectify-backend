package com.capstone.collectify.controllers.ClientModuleController;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.services.ClientModuleServices.ChatCollectorService;
import com.capstone.collectify.models.ClientModules.ChatCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/clientPage")
public class ChatCollectorController {

    @Autowired
    ChatCollectorService chatCollectorService;
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value="/chatCollector/{clientId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createChatCollector(@PathVariable Long clientId,@RequestBody ChatCollector chatCollector) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            chatCollectorService.createChatCollector(clientId,chatCollector);
            return new ResponseEntity<>("Chat Collector created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Chat Collector does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/chatCollector", method = RequestMethod.GET)
    public ResponseEntity<Object> getChatCollector() {
        return new ResponseEntity<>(chatCollectorService.getChatCollector(), HttpStatus.OK);
    }
    @RequestMapping(value="/chatCollector/client/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getChatCollectorByClientId(@PathVariable Long clientId) {
        return new ResponseEntity<>(chatCollectorService.getChatCollectorByClientId(clientId), HttpStatus.OK);
    }

    @RequestMapping(value = "/chatCollector/{chatCollectorid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteChatCollector(@PathVariable Long clientId,@PathVariable Long chatCollector_id) {
        return chatCollectorService.deleteChatCollector(clientId,chatCollector_id);
    }
    @RequestMapping(value="/chatCollector/{clientId}/{chatCollectorid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateChatCollector(@PathVariable Long clientId,@PathVariable Long chatCollector_id, @RequestBody ChatCollector chatCollector) {
        return chatCollectorService.updateChatCollector(clientId,chatCollector_id, chatCollector);
    }
}