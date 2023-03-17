package it.proactivity.recruiting.comparator;

import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.repository.JobPositionStatusRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class JobPositionComparatorSortByStatus implements Comparator<JobPosition> {

    @Autowired
    JobPositionStatusRepository jobPositionStatusRepository;

    @Override
    public int compare(JobPosition j1, JobPosition j2) {
        String status1 = j1.getJobPositionStatus().getName();
        String status2 = j2.getJobPositionStatus().getName();
        int value1 = getValue(status1);
        int value2 = getValue(status2);
        return Integer.compare(value1, value2);
    }

    private int getValue(String status) {
        String statusNew = jobPositionStatusRepository.getNewStatus();
        String statusUrgent = jobPositionStatusRepository.getUrgentStatus();

        if (StringUtils.isEmpty(status)) {
            return -1;
        }
        if (status.equals(statusUrgent)) {
            return 0;
        }

        if (status.equals(statusNew)) {
            return 1;
        }
        return 2;
    }
}
