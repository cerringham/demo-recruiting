package it.proactivity.recruiting.service;

import it.proactivity.recruiting.repository.JobInterviewStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobInterviewStatusService {
    @Autowired
    JobInterviewStatusRepository jobInterviewStatusRepository;
}
