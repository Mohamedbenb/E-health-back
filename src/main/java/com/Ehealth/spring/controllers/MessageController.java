package com.Ehealth.spring.controllers;

import com.Ehealth.spring.payload.dtos.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.security.Principal;
import java.util.*;

@Controller
public class MessageController {


    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, Set<String>> sessionUsersMap;
    private final Map<String, String> sessionUserMap;

    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.sessionUsersMap = new HashMap<>();
        this.sessionUserMap = new HashMap<>();
    }



    @MessageMapping("/sendMessage")

    public void handleMessage(SimpMessageHeaderAccessor headerAccessor, MessageDTO message) {
        String sessionId = headerAccessor.getSessionId();
        System.out.println("ssid  "+sessionId);
        Set<String> users = getUsersForSession(sessionId);
        System.out.println("users  "+users);
        System.out.println("sessionUsersMap In handle message "+sessionUsersMap);



        MessageDTO response = new MessageDTO();
        response.setText("Received your message: " + message.getText());


        for (String user : users) {
            System.out.println("ssid   "+user);
            messagingTemplate.convertAndSendToUser(user, "/topic/receiveMessage", message);
        }
        System.out.println("response     "+response);
    }
    private Set<String> getUsersForSession(String sessionId) {
        for (Map.Entry<String, Set<String>> entry : sessionUsersMap.entrySet()) {
            if (entry.getValue().contains(sessionId)) {
                return entry.getValue();
            }
        }
        return Collections.emptySet(); // Return an empty set if no users found
    }

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        System.out.println("called");
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        Principal principal = accessor.getUser();
        //String user = principal.getName();
        //sessionUserMap.put(sessionId, user);

        System.out.println("sessionUserMap: " + sessionUserMap);
        sessionUsersMap.computeIfAbsent(sessionId, key -> new HashSet<>()).add(sessionId);
        // Add the session to the sessionUsersMap
        sessionUsersMap.put(sessionId, new HashSet<>());

        System.out.println("sessionUsersMap  "+sessionUsersMap);
    }

    @EventListener
    public void handleSessionUnsubscribeEvent(SessionUnsubscribeEvent event) {
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();

        // Remove the session from the sessionUsersMap
        sessionUsersMap.remove(sessionId);
    }
}


