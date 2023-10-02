package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.ClientModules.ChatCollector;
import org.springframework.http.ResponseEntity;

public interface ChatCollectorService {
    void createChatCollector(Long clientId,ChatCollector chatCollector);
    Iterable<ChatCollector> getChatCollector();
    Iterable<ChatCollector> getChatCollectorByClientId(Long clientId);

    ResponseEntity deleteChatCollector(Long clientId,Long id);

    ResponseEntity updateChatCollector(Long clientId,Long id, ChatCollector chatCollector);
}
