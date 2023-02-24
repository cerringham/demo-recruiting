package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.ExpertiseDtoBuilder;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.dto.ExpertiseDto;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertiseService {

    @Autowired
    ExpertiseRepository expertiseRepository;

    @Autowired
    GlobalValidator globalValidator;


    public ResponseEntity<List<ExpertiseDto>> getAll() {

        List<Expertise> expertiseDtoList = expertiseRepository.findAll();

        List<ExpertiseDto> dtoList = expertiseDtoList.stream()
                .map(c -> createExpertiseDto(c.getName(), c.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<ExpertiseDto> getById(Long id) {

        globalValidator.validateId(id);

        Optional<Expertise> expertise = expertiseRepository.findById(id);

        if (expertise.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createExpertiseDto(expertise.get().getName(), expertise.get().getIsActive()));
    }

    private ExpertiseDto createExpertiseDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("the parameters for creating the expertise dto can't be null or empty");
        }

        return ExpertiseDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }
}

