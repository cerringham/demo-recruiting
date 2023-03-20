package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.dto.SimpleJobPositionDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class SimpleJobPositionStatusComparator implements Comparator<SimpleJobPositionDto> {
    @Override
    public int compare(SimpleJobPositionDto o1, SimpleJobPositionDto o2) {
        if(o1.getStatus().equals(o2.getStatus())) {
            return 0;
        } else if (o1.getStatus().equals("Urgent")) {
            return -1;
        } else if (o2.getStatus().equals("Urgent")) {
            return 1;
        } else if (o1.getStatus().equals("New")) {
            return -1;
        } else if (o2.getStatus().equals("New")) {
            return 1;
        } else {
            return o1.getStatus().compareTo(o2.getStatus());
        }

    }

}
