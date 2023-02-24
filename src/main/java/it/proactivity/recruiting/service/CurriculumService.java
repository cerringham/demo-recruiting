package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.CurriculumDtoBuilder;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.repository.CurriculumRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculumService {

    @Autowired
    CurriculumRepository curriculumRepository;

    @Autowired
    GlobalValidator globalValidator;

    public ResponseEntity<List<CurriculumDto>> getAll() {

        List<Curriculum> curriculumListList = curriculumRepository.findAll();

        List<CurriculumDto> dtoList = curriculumListList.stream()
                .map(c -> new CurriculumDto(c.getCandidate().getId(), c.getCandidate().getName(),
                        c.getCandidate().getSurname(), c.getSkill().getName(), c.getLevel().toString()))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CurriculumDto> getById(Long id) {
        globalValidator.validateId(id);

        Optional<Curriculum> curriculum = curriculumRepository.findById(id);

        if (curriculum.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createCurriculumDto(curriculum.get().getCandidate().getId(),
                curriculum.get().getCandidate().getName(), curriculum.get().getCandidate().getSurname(),
                curriculum.get().getSkill().getName(), curriculum.get().getLevel().toString()));
    }

    private CurriculumDto createCurriculumDto(Long candidateId, String candidateName, String candidateSurname,
                                              String skillName, String level) {
        if (candidateId == null || StringUtils.isEmpty(candidateName) || StringUtils.isEmpty(candidateSurname) ||
                StringUtils.isEmpty(skillName) || StringUtils.isEmpty(level)) {
            throw new IllegalArgumentException("The parameters for the creation of curriculum dto can't be null or empty");
        }

        return CurriculumDtoBuilder.newBuilder(candidateId)
                .candidateName(candidateName)
                .candidateSurname(candidateSurname)
                .skillName(skillName)
                .level(level)
                .build();


    }
}
