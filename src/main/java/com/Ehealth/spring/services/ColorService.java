package com.Ehealth.spring.services;

import com.Ehealth.spring.models.Color;
import com.Ehealth.spring.models.DateCal;
import com.Ehealth.spring.repository.ColorRepository;
import com.Ehealth.spring.repository.DateCalRepository;

import java.util.List;

public interface ColorService {
    List<Color> getAll();
    Color create(Color color);
    Color update(Long id, Color color);
    Color getById(Long id);
    Color getBytvId(Long id);

}
