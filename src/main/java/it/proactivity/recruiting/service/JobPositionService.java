package it.proactivity.recruiting.service;

import it.proactivity.recruiting.repository.JobPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobPositionService {
    @Autowired
    JobPositionRepository jobPositionRepository;
}
