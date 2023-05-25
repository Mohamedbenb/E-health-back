package com.Ehealth.spring.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NewVisiteEventListener implements ApplicationListener<NewVisiteEvent> {

    @Override
    public void onApplicationEvent(NewVisiteEvent event) {
        // Handle the event here
        System.out.println("NewVisiteEvent received for visite ID: " + event.getVisite().getId());
        // Perform any desired actions, such as displaying a notification or logging the event
    }
}
