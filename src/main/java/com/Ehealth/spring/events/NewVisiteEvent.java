package com.Ehealth.spring.events;

import com.Ehealth.spring.models.Visite;
import org.springframework.context.ApplicationEvent;

public class NewVisiteEvent extends ApplicationEvent {
    private final Visite visite;

    public NewVisiteEvent(Object source, Visite visite) {
        super(source);
        this.visite = visite;
    }

    public Visite getVisite() {
        return visite;
    }
}
