package com.viseo.c360.competence.converters.collaborator;

import com.viseo.c360.competence.domain.collaborator.Collaborator;
import com.viseo.c360.competence.dto.collaborator.CollaboratorDescription;
import com.viseo.c360.competence.dto.collaborator.CollaboratorIdentity;

import java.util.ArrayList;
import java.util.List;

public class IdentityToCollaborator {
    public IdentityToCollaborator() {
    }

    public Collaborator convert(CollaboratorIdentity dto) {
        Collaborator domain = new Collaborator();
        domain.setId(dto.getId());
        domain.setVersion(dto.getVersion());
        domain.setFirstName(dto.getFirstName());
        domain.setLastName(dto.getLastName());
        domain.setDefaultPicture(dto.getDefaultPicture());
        return domain;
    }

    public List<Collaborator> convert(List<CollaboratorIdentity> sourceList) {
        List<Collaborator> listCollaborator = new ArrayList<>();
        for (CollaboratorIdentity collaboratorIdentity : sourceList) {
            listCollaborator.add(convert(collaboratorIdentity));
        }
        return listCollaborator;
    }

}
