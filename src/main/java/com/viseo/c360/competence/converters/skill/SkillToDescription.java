package com.viseo.c360.competence.converters.skill;

import com.viseo.c360.competence.converters.collaborator.CollaboratorToIdentity;
import com.viseo.c360.competence.domain.skill.Skill;
import com.viseo.c360.competence.dto.skill.SkillDescription;

import java.util.ArrayList;
import java.util.List;

public class SkillToDescription {

    public SkillToDescription() {
    }

    public SkillDescription convert(Skill source) {
        SkillDescription dto = new SkillDescription();
        dto.setId(source.getId());
        dto.setVersion(source.getVersion());
        dto.setLabel(source.getLabel());
        dto.setCollaborators(new CollaboratorToIdentity().convert(source.getCollaborators()));
        return dto;
    }

    public List<SkillDescription> convert(List<Skill> sourceList) {
        List<SkillDescription> listDto = new ArrayList<>();
        for (Skill skill : sourceList) {
            listDto.add(convert(skill));
        }
        return listDto;
    }
}
