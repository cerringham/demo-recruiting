package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.CurriculumDtoBuilder;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.repository.CurriculumRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CurriculumService {
    @Autowired
    CurriculumRepository curriculumRepository;

    public ResponseEntity<Set<CurriculumDto>> getAll() {
        List<Curriculum> curriculumList = curriculumRepository.findAll();
        if (curriculumList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Set<CurriculumDto> dtoSet = curriculumList.stream()
                .map(c -> createCurriculumDto(c.getCandidate().getId(), c.getCandidate().getName(), c.getCandidate().getSurname(),
                        c.getSkill().toString(), c.getLevel().toString()))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(dtoSet);
    }
    public ResponseEntity<CurriculumDto> findById(Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Curriculum> curriculum = curriculumRepository.findById(id);
        if (curriculum.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CurriculumDto curriculumDto = createCurriculumDto(curriculum.get().getCandidate().getId(), curriculum.get().getCandidate().getName(),
                curriculum.get().getCandidate().getSurname(), curriculum.get().getSkill().toString(), curriculum.get().getLevel().toString());
        return ResponseEntity.ok(curriculumDto);
    }

    public CurriculumDto createCurriculumDto(Long candidateId, String candidateName, String candidateSurname,
                                             String skillName, String level) {
        if (candidateId == null || StringUtils.isEmpty(candidateName)|| StringUtils.isEmpty(candidateSurname) ||
                StringUtils.isEmpty(skillName) || StringUtils.isEmpty(level)) {
            throw new IllegalArgumentException();
        }
        return CurriculumDtoBuilder.newBuilder(candidateId)
                .candidateName(candidateName)
                .candidateSurname(candidateSurname)
                .skillName(skillName)
                .level(level)
                .buil();
    }
}
