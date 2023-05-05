package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.Declaration;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.models.MalProf;
import com.Ehealth.spring.models.UniOp;
import com.Ehealth.spring.repository.DeclarationRepository;
import com.Ehealth.spring.repository.EmployeeRepository;
import com.Ehealth.spring.repository.MalProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeclarationServiceImpl implements DeclarationService{
    @Autowired
    private DeclarationRepository declarationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MalProfRepository malProfRepository;
    @Override
    public Declaration create(Long empid, Long malid, Declaration declaration) {
        MalProf malProf = malProfRepository.findById(malid) .orElseThrow(()-> new ResourceNotFoundException("mal"+malid+ "id"));
        Employee employee = employeeRepository.findById(empid)  .orElseThrow(()-> new ResourceNotFoundException("emp"+empid+ "id"));
        declaration.setEmp(employee);
        declaration.setMal(malProf);
        try{

            return  declarationRepository.save(declaration);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Declaration> getall() {

        return declarationRepository.findAllByActive(true);

    }

    @Override
    public Declaration update( Long id, Long empid, Long malid,  Declaration declarationReq) {
        return declarationRepository.findById(id).map(declaration -> {
            declaration.setEmp(employeeRepository.findById(empid).orElseThrow(() -> new ResourceNotFoundException("id " + id + "not found")));
            declaration.setMal(malProfRepository.findById(malid).orElseThrow(() -> new ResourceNotFoundException("id " + id + "not found")));
            declaration.setTypederisque(declarationReq.getTypederisque());
            declaration.setConstat(declarationReq.getConstat());
            declaration.setDepotcnam(declarationReq.isDepotcnam());
            declaration.setDiagnosticcnam(declarationReq.isDiagnosticcnam());
            declaration.setReponsecnam(declarationReq.getReponsecnam());
            declaration.setRemarque(declarationReq.getRemarque());

            return declarationRepository.save(declaration);
        }).orElseThrow(() -> new ResourceNotFoundException("id " + id + "not found"));
    }

    @Override
    public Declaration delete(Long id) {
        Declaration declaration = null;

        try {
            declaration = declarationRepository.findById(id)

                    .orElseThrow(()-> new ResourceNotFoundException("id"+id+ "not found"));

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        declaration.setActive(false);
        return declarationRepository.save(declaration);
    }

    @Override
    public Declaration getone(Long id) {

        Optional<Declaration> result = declarationRepository.findById(id);

        if(result.isPresent()){
            return result.get();

        }else{
            try {
                throw new ResourceNotFoundException("declaration"+id+ "id");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }


}

