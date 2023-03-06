package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.CurriculumDtoBuilder;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.CurriculumRepository;
import it.proactivity.recruiting.utility.CandidateUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.SkillUtility;
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

    @Autowired
    CandidateUtility candidateUtility;

    @Autowired
    SkillUtility skillUtility;

    public ResponseEntity<List<CurriculumDto>> getAll() {

        List<Curriculum> curriculumListList = curriculumRepository.findByIsActive(true);

        List<CurriculumDto> dtoList = curriculumListList.stream()
                .map(c -> new CurriculumDto(candidateUtility.createCandidateDto(c.getCandidate()),
                        skillUtility.createSkillDto(c.getSkill()), c.getLevel().toString()))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CurriculumDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<Curriculum> curriculum = curriculumRepository.findByIdAndIsActive(id, true);

        if (curriculum.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createCurriculumDto(candidateUtility.createCandidateDto(curriculum.get().getCandidate()),
                skillUtility.createSkillDto(curriculum.get().getSkill()), curriculum.get().getLevel().toString()));
    }

    private CurriculumDto createCurriculumDto(CandidateDto candidateDto, SkillDto skillDto, String level) {
        if (candidateDto == null || skillDto == null|| StringUtils.isEmpty(level)) {
            throw new IllegalArgumentException("The parameters for the creation of curriculum dto can't be null or empty");
        }
        return CurriculumDtoBuilder.newBuilder(candidateDto)
                .skillDto(skillDto)
                .level(level)
                .build();


    }
}
