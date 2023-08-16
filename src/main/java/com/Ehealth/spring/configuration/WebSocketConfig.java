package com.Ehealth.spring.configuration;

import com.Ehealth.spring.configuration.MessageWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // Enable a simple in-memory message broker
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket") // Define the WebSocket endpoint URL
                .setAllowedOriginPatterns("*").withSockJS();


                // Enable SockJS fallback options if needed
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(512 * 1024) // Set a maximum message size if needed
                .setSendBufferSizeLimit(1024 * 1024) // Set a maximum send buffer size if needed
                .setSendTimeLimit(20 * 10000); // Set a send time limit if needed
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // Configure the client inbound channel if needed
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        // Configure the client outbound channel if needed
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        // Configure message converters if needed
        return true;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // Add argument resolvers if needed
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        // Add return value handlers if needed
    }



    @Bean
    public WebSocketHandler messageWebSocketHandler() {
        return new MessageWebSocketHandler();
    }
}
