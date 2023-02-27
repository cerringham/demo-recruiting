package it.proactivity.recruiting.service;

import it.proactivity.recruiting.repository.JobInterviewTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobInterviewTypeService {
    @Autowired
    JobInterviewTypeRepository jobInterviewTypeRepository;
}
