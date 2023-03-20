package it.proactivity.recruiting.comparator;

import it.proactivity.recruiting.model.JobPosition;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class JobPositionComparatorSortByStatus implements Comparator<JobPosition> {
    @Override
    public int compare(JobPosition j1, JobPosition j2) {
        Integer priority1 = j1.getJobPositionStatus().getPriority();
        Integer priority2 = j2.getJobPositionStatus().getPriority();
        return Integer.compare(priority1, priority2);
    }
}
