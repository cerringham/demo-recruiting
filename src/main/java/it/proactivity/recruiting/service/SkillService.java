package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.SkillRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.SkillUtility;
import org.apache.commons.text.WordUtils;
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

    @Autowired
    SkillUtility skillUtility;

    public ResponseEntity<List<SkillDto>> getAll() {
        List<Skill> skillList = skillRepository.findByIsActive(true);

        List<SkillDto> dtoList = skillList.stream()
                .map(s -> skillUtility.createSkillDto(s.getName(), s.getIsActive()))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<SkillDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<Skill> skill = skillRepository.findByIdAndIsActive(id, true);

        if (skill.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(skillUtility.createSkillDto(skill.get().getName(), skill.get().getIsActive()));
    }

    public ResponseEntity insertSkill(SkillDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("skill dto can't be null");
        }

        globalValidator.validateStringAlphaNumericSpace(dto.getName());

        Optional<Skill> checkSkill = skillRepository.findByNameIgnoreCase(WordUtils.capitalizeFully(dto.getName()));
        if (checkSkill.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }

        Skill skill = skillUtility.createSkill(dto);
        skillRepository.save(skill);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity deleteSkill(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        Optional<Skill> skill = skillRepository.findByIdAndIsActive(id, true);
        if (skill.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        skill.get().setIsActive(false);
        skillRepository.save(skill.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity updateSkill(SkillDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dto can't be null");
        }
        Optional<Skill> skill = skillRepository.findByIdAndIsActive(dto.getId(), true);
        if (skill.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        Optional<Skill> checkExistingSkill = skillRepository.findByNameIgnoreCase(dto.getName());
        if (checkExistingSkill.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        skill.get().setName(WordUtils.capitalizeFully(dto.getName()));
        skillRepository.save(skill.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
