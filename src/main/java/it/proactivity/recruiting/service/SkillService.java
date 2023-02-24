package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.SkillDtoBuilder;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.SkillRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    GlobalValidator globalValidator;

    public ResponseEntity<List<SkillDto>> getAll() {
        List<Skill> skillList = skillRepository.findAll();

        List<SkillDto> dtoList = skillList.stream()
                .map(s -> createSkillDto(s.getName(), s.getIsActive()))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<SkillDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<Skill> skill = skillRepository.findById(id);

        if (skill.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createSkillDto(skill.get().getName(), skill.get().getIsActive()));
    }

    private SkillDto createSkillDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of skillDto can't be null or empty");
        }

        return SkillDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }
}
