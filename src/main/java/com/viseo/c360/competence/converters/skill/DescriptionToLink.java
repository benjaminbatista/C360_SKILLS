package com.viseo.c360.competence.converters.skill;


import com.viseo.c360.competence.converters.collaborator.IdentityToCollaborator;
import com.viseo.c360.competence.domain.skill.Link;
import com.viseo.c360.competence.domain.skill.Skill;
import com.viseo.c360.competence.dto.skill.LinkDescription;
import com.viseo.c360.competence.dto.skill.SkillDescription;

import java.util.ArrayList;
import java.util.List;

public class DescriptionToLink {

    public DescriptionToLink() {
    }

    public Link convert(LinkDescription dto) {
        Link domain = new Link();
        domain.setId(dto.getId());
        domain.setVersion(dto.getVersion());
        domain.setSkill1(new DescriptionToSkill().convert(dto.getSkill1()));
        domain.setSkill2(new DescriptionToSkill().convert(dto.getSkill2()));
        return domain;
    }

    public List<Link> convert(List<LinkDescription> sourceList) {
        List<Link> listLink = new ArrayList<>();
        for (LinkDescription linkDescription : sourceList) {
            listLink.add(convert(linkDescription));
        }
        return listLink;
    }
}
