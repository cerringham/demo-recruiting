package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.JobPositionStatus;
import it.proactivity.recruiting.repository.JobPositionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

@Component
public class JobPositionStatusComparator implements Comparator<JobPositionStatus> {

    @Autowired
    JobPositionStatusRepository jobPositionStatusRepository;
    @Override
    public int compare(JobPositionStatus o1, JobPositionStatus o2) {

        Optional<JobPositionStatus> newStatus = jobPositionStatusRepository.findByName("New");
        Optional<JobPositionStatus> urgentStatus = jobPositionStatusRepository.findByName("Urgent");
        Optional<JobPositionStatus> closedStatus = jobPositionStatusRepository.findByName("Closed");
        if(o1.getId().equals(o2.getId())) {
            return 0;
        } else if (o1.getId().equals(urgentStatus.get().getId())) {
            return -1;
        } else if (o2.getId().equals(urgentStatus.get().getId())) {
            return 1;
        } else if (o1.getId().equals(newStatus.get().getId())) {
            return -1;
        } else if (o2.getId().equals(newStatus.get().getId())) {
            return 1;
        } else if (o1.getId().equals(closedStatus.get().getId())) {
            return 1;
        } else if (o2.getId().equals(closedStatus.get().getId())) {
            return -1;
        }else {
            return o1.getId().compareTo(o2.getId());
        }

    }

}
