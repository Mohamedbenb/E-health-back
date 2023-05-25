package com.Ehealth.spring.events;

import com.Ehealth.spring.models.Visite;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class VisiteEventPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public VisiteEventPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void publishNewVisiteEvent(Visite visite) {
        messagingTemplate.convertAndSend("/topic/new-visite", visite);
    }
}

