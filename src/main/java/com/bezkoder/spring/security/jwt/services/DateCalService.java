package com.bezkoder.spring.security.jwt.services;

import com.bezkoder.spring.security.jwt.models.DateCal;
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
