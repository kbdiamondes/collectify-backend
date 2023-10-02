package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.ChatCollector;
import com.capstone.collectify.repositories.ClientModuleRepository.ChatCollectorRepository;
import com.capstone.collectify.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatCollectorServiceImpl implements ChatCollectorService{
    @Autowired
    private ChatCollectorRepository chatCollectorRepository;
    @Autowired
    private ClientRepository clientRepository;
    //Create ChatCollector
    public void createChatCollector(Long clientId,ChatCollector chatCollector){
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            chatCollector.setClient(client);
            chatCollectorRepository.save(chatCollector);
        }
    }
    // Get All ChatCollector
    public Iterable<ChatCollector> getChatCollector(){
        return chatCollectorRepository.findAll();
    }
    public Iterable<ChatCollector> getChatCollectorByClientId(Long clientId){
       return chatCollectorRepository.findChatCollectorByClientId(clientId);
    }

    // Delete Chat Collector
    public ResponseEntity deleteChatCollector(Long clientId,Long id){
        ChatCollector chatCollectorToDelete = chatCollectorRepository.findById(id).orElse(null);
        if (chatCollectorToDelete != null && chatCollectorToDelete.getClient().getClient_id().equals(clientId)) {
            chatCollectorRepository.deleteById(id);
            return new ResponseEntity<>("Chat Collector Deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Chat Collector not found or does not belong to the specified client", HttpStatus.NOT_FOUND);
        }
    }
    // Update
    public ResponseEntity updateChatCollector(Long clientId,Long id, ChatCollector chatCollector){
        ChatCollector chatCollectorForUpdate = chatCollectorRepository.findById(id).orElse(null);
        if (chatCollectorForUpdate != null && chatCollectorForUpdate.getClient().getClient_id().equals(clientId)) {
            chatCollectorForUpdate.setClientMessage(chatCollector.getClientMessage());
            chatCollectorRepository.save(chatCollectorForUpdate);
            return new ResponseEntity<>("Due Payments Updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Due payment not found",HttpStatus.NOT_FOUND);
    }

}
