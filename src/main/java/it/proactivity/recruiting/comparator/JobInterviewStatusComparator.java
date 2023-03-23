package it.proactivity.recruiting.comparator;

import it.proactivity.recruiting.model.JobInterviewStatus;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class JobInterviewStatusComparator implements Comparator<JobInterviewStatus> {

    @Override
    public int compare(JobInterviewStatus o1, JobInterviewStatus o2) {
        return Integer.compare(o1.getSequence(), o2.getSequence());
    }
}
