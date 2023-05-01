package com.Ehealth.spring.services;

import com.Ehealth.spring.models.DateCal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface DateCalService {
    List<DateCal> getAllDateCals();

    DateCal createDateCal(DateCal employee);

    DateCal updateDateCal(long id, DateCal employee);

    DateCal deleteDateCal(long id);

    DateCal getDateCalById(long id);
}
