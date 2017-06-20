package com.viseo.c360.competence.converters.skill;

import com.viseo.c360.competence.converters.collaborator.IdentityToCollaborator;
import com.viseo.c360.competence.domain.skill.Skill;
import com.viseo.c360.competence.dto.skill.SkillDescription;

import java.util.ArrayList;
import java.util.List;

public class DescriptionToSkill {

    public DescriptionToSkill() {
    }

    public Skill convert(SkillDescription dto) {
        Skill domain = new Skill();
        domain.setId(dto.getId());
        domain.setVersion(dto.getVersion());
        domain.setLabel(dto.getLabel());
        domain.setCollaborators(new IdentityToCollaborator().convert(dto.getCollaborators()));
        return domain;
    }

    public List<Skill> convert(List<SkillDescription> sourceList) {
        List<Skill> listCollaborator = new ArrayList<>();
        for (SkillDescription skillDescription : sourceList) {
            listCollaborator.add(convert(skillDescription));
        }
        return listCollaborator;
    }
}
