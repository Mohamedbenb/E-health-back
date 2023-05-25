package com.Ehealth.spring.services;

import com.Ehealth.spring.events.ColorEventPublisher;
import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.Color;
import com.Ehealth.spring.repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ColorEventPublisher colorEventPublisher;
    public ColorServiceImpl(ColorRepository colorRepository, ColorEventPublisher colorEventPublisher) {
        super();
        this.colorRepository = colorRepository;
        this.colorEventPublisher=colorEventPublisher;
    }

    @Override
    public List<Color> getAll() {
        try {
            return colorRepository.findAll();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Color create(Color color) {

        colorEventPublisher.publishNewColorEvent(color);
        return colorRepository.save(color);

    }

    @Override
    public Color update(Long id, Color colorReq) {
        Color color = colorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("color"+id+"not found"));
        color.setPrimary(colorReq.getPrimary());
        color.setSecondary(colorReq.getSecondary());
        return colorRepository.save(color);
    }

    @Override
    public Color getById(Long id) {
        return  colorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("color"+id+"not found"));
    }

    @Override
    public Color getBytvId(Long id) {
        return null;
    }
}


