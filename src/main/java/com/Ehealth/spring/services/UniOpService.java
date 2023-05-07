package com.Ehealth.spring.services;

import com.Ehealth.spring.models.UniOp;

import java.util.List;

public interface UniOpService {

    List<UniOp> getAllUniOps();

    UniOp createUniOp(Long societeId,UniOp uniop);

    UniOp updateUniOp(Long societeId, Long id, UniOp uniop);

    UniOp deleteUniOp(Long societeId,Long id);

    UniOp getUniOpById(Long societeId,Long id, boolean b);
}

