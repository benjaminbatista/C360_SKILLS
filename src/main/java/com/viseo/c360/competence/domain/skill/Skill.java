package com.viseo.c360.competence.domain.skill;

import com.viseo.c360.competence.domain.BaseEntity;
import com.viseo.c360.competence.domain.collaborator.Collaborator;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Skill extends BaseEntity {

    @NotNull
    @Size(max = 20)
    String label;

    @Valid
    @ManyToMany
    @JoinTable(name = "links")
    List<Skill> links;

    @Valid
    @ManyToMany
    @JoinTable(name = "skill_owners")
    List<Collaborator> collaborators;

    public Skill() {
        super();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Skill> getLinks() {
        return links;
    }


    public void addSkill(Skill skill) {
        if (this.links.indexOf(skill) == -1) {
            this.links.add(skill);
            skill.simpleAdd(this);
        }
    }

    public void removeSkill(Skill skill) {
        if (this.links.indexOf(skill) != -1) {
            this.links.remove(skill);
            skill.simpleRemove(this);
        }
    }

    public void simpleAdd(Skill skill) {
        this.links.add(skill);
    }

    public void simpleRemove(Skill skill) {
        this.links.remove(skill);
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void addCollaborator(Collaborator collaborator) {
        this.collaborators.add(collaborator);
    }

    public void removeCollaborator(Collaborator collaborator) {
        this.collaborators.remove(collaborator);
    }

    public void setLinks(List<Skill> links) {
        this.links = links;
    }

    public void setCollaborators(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }
}

