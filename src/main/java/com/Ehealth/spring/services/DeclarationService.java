package com.Ehealth.spring.services;

import com.Ehealth.spring.models.Declaration;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.models.MalProf;
import com.Ehealth.spring.models.UniOp;

import java.util.List;

public interface DeclarationService {
    Declaration create(Long empid, Long malid,Declaration declaration);
    List<Declaration> getall();
    Declaration update(Long malid, Long empid,Long id,Declaration declaration);
    Declaration delete(Long id);
    Declaration getone(Long id);
    List<Declaration> getByEmployee(Long employeeId, boolean b);
    List<Declaration> getByMal(Long malId, boolean b);

}
