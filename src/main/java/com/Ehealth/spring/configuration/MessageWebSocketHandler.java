package com.Ehealth.spring.configuration;

import org.springframework.web.socket.*;

public class MessageWebSocketHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // This method is called when a new WebSocket connection is established
        // You can perform any necessary initialization here
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // This method is called when a new WebSocket message is received
        // You can process the message here and perform any necessary actions

        String payload = message.getPayload().toString();
        // Process the payload and perform actions based on the message content

        // Example: Send a response back to the client
        session.sendMessage(new TextMessage("Received your message: " + payload));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // This method is called when a WebSocket transport error occurs
        // You can handle the error here, log it, or take any necessary actions
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // This method is called when a WebSocket connection is closed
        // You can perform any necessary cleanup or logging here
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
