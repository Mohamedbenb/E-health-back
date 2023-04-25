package com.bezkoder.spring.security.jwt.services;

import com.bezkoder.spring.security.jwt.exception.ResourceNotFoundException;
import com.bezkoder.spring.security.jwt.models.DateCal;
import com.bezkoder.spring.security.jwt.repository.DateCalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DateCalServiceImpl implements DateCalService {

    private final DateCalRepository datecalRepository;

    public DateCalServiceImpl(DateCalRepository datecalRepository) {
        super();
        this.datecalRepository = datecalRepository;
    }
    @Override
    public List<DateCal> getAllDateCals(){

        return datecalRepository.findAllByStatus();
    }
    @Override
    public DateCal createDateCal(DateCal datecal){
        return datecalRepository.save(datecal);
    }
    @Override
    public DateCal updateDateCal(long id, DateCal datecalRequest){
        DateCal datecal = null;
        try {
            datecal = datecalRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("DateCal"+id+ "id"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        DateCal.Color color = new DateCal.Color();
        datecal.setTitle(datecalRequest.getTitle());
        datecal.setStart(datecalRequest.getStart());
        datecal.setEnd(datecalRequest.getEnd());
        color.setPrimary(datecalRequest.getColor().getPrimary());
        color.setSecondary(datecalRequest.getColor().getSecondary());

        datecal.setColor(color);
        datecal.setDraggable(datecalRequest.getDraggable());
        return datecalRepository.save(datecal);
    }

    @Override
    public DateCal deleteDateCal(long id) {
        DateCal datecal = null;

        try {
            datecal = datecalRepository.findById(id)

                    .orElseThrow(()-> new ResourceNotFoundException("DateCal"+id+"id"));

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        datecal.setActive(false);
        return datecalRepository.save(datecal);

    }

    @Override
    public DateCal getDateCalById(long id) {
        Optional<DateCal> result = datecalRepository.findById(id);
        if(result.isPresent()){
            return result.get();

        }else{
            try {
                throw new ResourceNotFoundException("DateCal"+id+"id");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }




}