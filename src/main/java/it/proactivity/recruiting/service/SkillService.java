package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.SkillBuilder;
import it.proactivity.recruiting.builder.SkillDtoBuilder;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.SkillRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.apache.commons.lang3.StringUtils;
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

    public ResponseEntity<List<SkillDto>> getAll() {
        List<Skill> skillList = skillRepository.findByIsActive(true);

        List<SkillDto> dtoList = skillList.stream()
                .map(s -> createSkillDto(s.getName(), s.getIsActive()))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<SkillDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<Skill> skill = skillRepository.findByIdAndIsActive(id, true);

        if (skill.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createSkillDto(skill.get().getName(), skill.get().getIsActive()));
    }

    public ResponseEntity insertSkill(SkillDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("skill dto can't be null");
        }

        globalValidator.validateStringAlphaNumericSpace(dto.getName());
        //cerco nel db se la skill esiste
        Optional<Skill> checkSkill = skillRepository.findByNameIgnoreCase(WordUtils.capitalizeFully(dto.getName()));
        if (checkSkill.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
        //creo la skill inserendo il nome nel formato corretto
        Skill skill = createSkill(dto);
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
        Optional<Skill> skill = skillRepository.findById(dto.getId());
        if (skill.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //controllo che il nuovo valore che assegnerò alla skill non sia già presente
        Optional<Skill> checkExistingSkill = skillRepository.findByNameIgnoreCase(dto.getName());
        if (checkExistingSkill.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        skill.get().setName(WordUtils.capitalizeFully(dto.getName()));
        skillRepository.save(skill.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private SkillDto createSkillDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of skillDto can't be null or empty");
        }

        return SkillDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }

    private Skill createSkill(SkillDto dto) {
        return SkillBuilder.newBuilder(WordUtils.capitalizeFully(dto.getName()))
                .isActive(dto.getIsActive())
                .build();
    }

}
