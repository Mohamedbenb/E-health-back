package com.Ehealth.spring.events;

import com.Ehealth.spring.models.Color;
import com.Ehealth.spring.models.Visite;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ColorEventPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public ColorEventPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void publishNewColorEvent(Color color) {
        messagingTemplate.convertAndSend("/topic/new-color", color);
    }
}
