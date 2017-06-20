package com.viseo.c360.competence.domain.skill;

import com.viseo.c360.competence.domain.BaseEntity;
import com.viseo.c360.competence.domain.collaborator.Collaborator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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


    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void addCollaborator(Collaborator collaborator) {
        this.collaborators.add(collaborator);
    }

    public void removeCollaborator(Collaborator collaborator) {
        this.collaborators.remove(collaborator);
    }

    public void setCollaborators(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }
}

