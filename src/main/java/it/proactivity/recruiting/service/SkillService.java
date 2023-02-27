package it.proactivity.recruiting.service;

import it.proactivity.recruiting.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
    @Autowired
    SkillRepository skillRepository;
}
