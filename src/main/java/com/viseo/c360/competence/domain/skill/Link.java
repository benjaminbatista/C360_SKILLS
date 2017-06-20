package com.viseo.c360.competence.domain.skill;

import com.viseo.c360.competence.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Link extends BaseEntity {

    @NotNull
    @ManyToOne
    Skill skill1;

    @NotNull
    @ManyToOne
    Skill skill2;

    public Link() {
        super();
    }

    public Skill getSkill1() {
        return skill1;
    }

    public void setSkill1(Skill skill1) {
        this.skill1 = skill1;
    }

    public Skill getSkill2() {
        return skill2;
    }

    public void setSkill2(Skill skill2) {
        this.skill2 = skill2;
    }
}
