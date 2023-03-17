package it.proactivity.recruiting.comparator;

import it.proactivity.recruiting.model.JobPosition;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class JobPositionComparatorSortByStatus implements Comparator<JobPosition> {
    @Override
    public int compare(JobPosition j1, JobPosition j2) {
        String status1 = j1.getJobPositionStatus().getName();
        String status2 = j2.getJobPositionStatus().getName();
        return status2.compareTo(status1);
    }
}
