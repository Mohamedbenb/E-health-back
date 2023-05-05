package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.Declaration;
import com.Ehealth.spring.models.MalProf;
import com.Ehealth.spring.repository.DateCalRepository;
import com.Ehealth.spring.repository.MalProfRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MalProfServiceImpl implements MalProfService {
    private final MalProfRepository malprofRepository;
    public MalProfServiceImpl(MalProfRepository malprofrepository) {
        super();
        this.malprofRepository = malprofrepository;
    }
    @Override
    public MalProf create(MalProf malProf) {
        try{
            return  malprofRepository.save(malProf);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MalProf> getAll() {
        return malprofRepository.findAllByActive(true);
    }

    @Override
    public MalProf update(Long id, MalProf malprofreq) {
        System.out.println(id);
        return malprofRepository.findById(id).map(malprof->{

            malprof.setTitle(malprofreq.getTitle());
            malprof.setNbr(malprofreq.getNbr());
            malprof.setTypedelai(malprofreq.getTypedelai());
            return malprofRepository.save(malprof);
        }).orElseThrow(()->new ResourceNotFoundException("resource not found " + id + "not found"));
    }

    @Override
    public MalProf delete(Long id) {
        MalProf malprof = null;

        try {
            malprof = malprofRepository.findById(id)

                    .orElseThrow(()-> new ResourceNotFoundException("resource "+id+ "not found"));

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        malprof.setActive(false);
        return malprofRepository.save(malprof);
    }

    @Override
    public MalProf getone(Long id) {
        MalProf malprof = null;
        try {
            malprof = malprofRepository.findById(id)

                    .orElseThrow(()-> new ResourceNotFoundException("id"+id+ "not found"));

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        return malprof;
    }
}
