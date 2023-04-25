package com.bezkoder.spring.security.jwt.services;

import com.bezkoder.spring.security.jwt.exception.ResourceNotFoundException;
import com.bezkoder.spring.security.jwt.models.UniOp;
import com.bezkoder.spring.security.jwt.repository.SocieteRepository;
import com.bezkoder.spring.security.jwt.repository.UniOpRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<UniOp> getAllUniOps(Long societeId){

        return uniOpRepository.findBySocieteId(societeId);
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
    public UniOp getUniOpById(Long societeId, Long uniOpid) {
        Optional<UniOp> result = uniOpRepository.findByIdAndSocieteId(societeId,uniOpid);
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
