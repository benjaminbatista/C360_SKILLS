package com.viseo.c360.competence.dto.collaborator;

import com.viseo.c360.competence.dto.BaseDTO;
import com.viseo.c360.competence.dto.skill.SkillDescription;

import java.util.List;

public class CollaboratorIdentity extends BaseDTO {
    String lastName;
    String firstName;
    String email;
    Boolean defaultPicture;
    List<SkillDescription> links;

    public CollaboratorIdentity() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDefaultPicture() {
        return defaultPicture;
    }

    public void setDefaultPicture(Boolean defaultPicture) {
        this.defaultPicture = defaultPicture;
    }

    public List<SkillDescription> getLinks() {
        return links;
    }

    public void setLinks(List<SkillDescription> links) {
        this.links = links;
    }
}
