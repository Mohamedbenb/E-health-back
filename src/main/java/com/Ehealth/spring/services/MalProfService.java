package com.Ehealth.spring.services;

import com.Ehealth.spring.models.Declaration;
import com.Ehealth.spring.models.MalProf;

import java.util.List;

public interface MalProfService {
    MalProf create (MalProf malProf);
    List<MalProf> getAll();
    MalProf update(Long id, MalProf malProf);
    MalProf delete(Long id);
    MalProf getone(Long id);
}
