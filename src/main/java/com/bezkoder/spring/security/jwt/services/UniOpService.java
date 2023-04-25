package com.bezkoder.spring.security.jwt.services;

import com.bezkoder.spring.security.jwt.models.UniOp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UniOpService {

    List<UniOp> getAllUniOps(Long societeId);

    UniOp createUniOp(Long societeId,UniOp uniop);

    UniOp updateUniOp(Long societeId, Long id, UniOp uniop);

    UniOp deleteUniOp(Long societeId,Long id);

    UniOp getUniOpById(Long societeId,Long id);
}

