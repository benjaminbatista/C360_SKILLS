package com.viseo.c360.competence.dto.skill;
import com.viseo.c360.competence.dto.BaseDTO;


public class LinkDescription extends BaseDTO {

    SkillDescription skill1;
    SkillDescription skill2;

    public LinkDescription() {
    }

    public SkillDescription getSkill1() {
        return skill1;
    }

    public void setSkill1(SkillDescription skill1) {
        this.skill1 = skill1;
    }

    public SkillDescription getSkill2() {
        return skill2;
    }

    public void setSkill2(SkillDescription skill2) {
        this.skill2 = skill2;
    }
}

