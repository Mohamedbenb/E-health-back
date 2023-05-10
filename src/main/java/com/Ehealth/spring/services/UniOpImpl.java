package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.UniOp;
import com.Ehealth.spring.repository.SocieteRepository;
import com.Ehealth.spring.repository.UniOpRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniOpImpl implements UniOpService {

    private final UniOpRepository uniOpRepository;
    private final SocieteRepository societeRepository;
    public UniOpImpl(UniOpRepository UniOpRepository, SocieteRepository SocieteRepository) {
        super();
        this.uniOpRepository = UniOpRepository;
        this.societeRepository = SocieteRepository;
    }
    @Override
    public List<UniOp> getAllUniOps(){

        return uniOpRepository.findAllByActive();
    }
    @Override
    public UniOp createUniOp(Long societeId, UniOp uniOp)
    {
        return societeRepository.findById(societeId).map(societe -> {
            uniOp.setSociete(societe);
            return uniOpRepository.save(uniOp);

        }).orElseThrow(() -> new ResourceNotFoundException("societed " + societeId + " not found"));

    }
    @Override
    public UniOp updateUniOp(Long societeId, Long uniOpId, UniOp uniOpRequest){

        if(!societeRepository.existsById(societeId)) {
            throw new ResourceNotFoundException("societeId " + societeId + " not found");
        }

        return uniOpRepository.findById(uniOpId).map(uniOp -> {
            uniOp.setTitle(uniOpRequest.getTitle());
            uniOp.setReshum(uniOpRequest.getReshum());
            return uniOpRepository.save(uniOp);
        }).orElseThrow(() -> new ResourceNotFoundException("uniOpId " + uniOpId + "not found"));
    }

    @Override
    public UniOp deleteUniOp(Long societeId, Long uniOpid) {
        UniOp UniOp = null;

        try {
            UniOp = uniOpRepository.findById(uniOpid)

                    .orElseThrow(()-> new ResourceNotFoundException("UniOp"+uniOpid+ "id"));

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        UniOp.setActive(false);
        return uniOpRepository.save(UniOp);

    }

    @Override
    public UniOp getUniOpById(Long societeId, Long uniOpid, boolean b) {
        Optional<UniOp> result = uniOpRepository.findByIdAndSocieteIdAndActive(societeId,uniOpid, true);
        if(result.isPresent()){
            return result.get();

        }else{
            try {
                throw new ResourceNotFoundException("UniOp"+uniOpid+ "id");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
