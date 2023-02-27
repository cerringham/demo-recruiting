package it.proactivity.recruiting.service;

import it.proactivity.recruiting.repository.JobPositionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobPositionStatusService {
    @Autowired
    JobPositionStatusRepository jobPositionStatusRepository;
}
