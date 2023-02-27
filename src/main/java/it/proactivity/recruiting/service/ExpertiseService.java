package it.proactivity.recruiting.service;

import it.proactivity.recruiting.repository.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertiseService {
    @Autowired
    ExpertiseRepository expertiseRepository;
}
