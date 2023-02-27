package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.service.JobInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInterviewController {
    @Autowired
    JobInterviewService jobInterviewService;
}
