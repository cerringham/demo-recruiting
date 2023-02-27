package it.proactivity.recruiting.service;

import it.proactivity.recruiting.repository.JobInterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobInterviewService {
    @Autowired
    JobInterviewRepository jobInterviewRepository;
}
