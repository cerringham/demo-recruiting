package it.proactivity.recruiting.mapper;

import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);


    @Mapping(source = "candidateSkillList", target = "candidateSkillListIds")
    @Mapping(source = "skillList", target = "skillLevelIds")
    SkillDto skillToSkillSto(Skill skill);
}
