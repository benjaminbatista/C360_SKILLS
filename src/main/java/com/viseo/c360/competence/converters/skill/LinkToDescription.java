package com.viseo.c360.competence.converters.skill;

import com.viseo.c360.competence.converters.collaborator.CollaboratorToIdentity;
import com.viseo.c360.competence.domain.skill.Link;
import com.viseo.c360.competence.domain.skill.Skill;
import com.viseo.c360.competence.dto.skill.LinkDescription;
import com.viseo.c360.competence.dto.skill.SkillDescription;

import java.util.ArrayList;
import java.util.List;

public class LinkToDescription {

    public LinkToDescription() {
    }

    public LinkDescription convert(Link source) {
        LinkDescription dto = new LinkDescription();
        dto.setId(source.getId());
        dto.setVersion(source.getVersion());
        dto.setSkill1(new SkillToDescription().convert(source.getSkill1()));
        dto.setSkill2(new SkillToDescription().convert(source.getSkill2()));
        return dto;
    }

    public List<LinkDescription> convert(List<Link> sourceList) {
        List<LinkDescription> listDto = new ArrayList<>();
        for (Link link : sourceList) {
            listDto.add(convert(link));
        }
        return listDto;
    }
}
