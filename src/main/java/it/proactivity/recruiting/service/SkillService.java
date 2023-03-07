package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.SkillBuilder;
import it.proactivity.recruiting.builder.SkillDtoBuilder;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.SkillRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.SkillValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    SkillValidator skillValidator;

    public ResponseEntity<Set<SkillDto>> getAll() {
        List<Skill> skillList = skillRepository.findByIsActive(true);

        Set<SkillDto> skillDtos = skillList.stream()
                .map(s -> createSkillDto(s.getName(), s.getIsActive()))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(skillDtos);
    }

    public ResponseEntity<SkillDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<Skill> skill = skillRepository.findByIdAndIsActive(id, true);

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

    public ResponseEntity<SkillDto> deleteSkill(Long id) {
        if (!globalValidator.validateId(id)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Skill> skill = skillRepository.findByIdAndIsActive(id, true);
        if (skill.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        skill.get().setIsActive(false);
        skillRepository.save(skill.get());
        SkillDto skillDto = SkillDtoBuilder.newBuilder(skill.get().getName()).isActive(false).build();
        return ResponseEntity.ok(skillDto);
    }

    public ResponseEntity<SkillDto> insertSkill(SkillDto skillDto) {
        if (!skillValidator.validateSkillParameters(skillDto)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Skill> skill = skillRepository.findByNameIgnoreCaseAndIsActive(skillDto.getName(), true);
        if (skill.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        if (!skillValidator.validateSkillName(skillDto.getName())) {
            return ResponseEntity.badRequest().build();
        }
        Skill newSkill = SkillBuilder.newBuilder(skillDto.getName()).isActive(true).build();
        skillRepository.save(newSkill);
        return ResponseEntity.ok(skillDto);
    }

    public ResponseEntity<SkillDto> updateSkill(SkillDto skillDto) {
        if (!skillValidator.validateSkillParameters(skillDto)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Skill> skill = skillRepository.findByIdAndIsActive(skillDto.getId(), true);
        if (skill.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (!skillValidator.validateSkillName(skillDto.getName())) {
            return ResponseEntity.badRequest().build();
        }
        skill.get().setName(skillDto.getName());
        skill.get().setIsActive(skillDto.getIsActive());
        skillRepository.save(skill.get());
        return ResponseEntity.ok(skillDto);
    }
}
