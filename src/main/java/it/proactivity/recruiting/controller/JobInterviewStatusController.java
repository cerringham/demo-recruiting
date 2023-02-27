package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.service.JobInterviewStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInterviewStatusController {
    @Autowired
    JobInterviewStatusService jobInterviewStatusService;
}
