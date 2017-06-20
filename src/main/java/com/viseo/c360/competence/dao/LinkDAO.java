package com.viseo.c360.competence.dao;

import com.viseo.c360.competence.dao.db.DAOFacade;
import com.viseo.c360.competence.domain.skill.Link;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class LinkDAO {
    @Inject
    DAOFacade daoFacade;

    @Transactional
    public Link addLink(Link link) throws PersistenceException {
        daoFacade.persist(link);
        daoFacade.flush();
        return link;
    }

    @Transactional
    public Link removeLink(Link link) throws PersistenceException{
        daoFacade.remove(link);
        daoFacade.flush();
        return link;
    }

    @Transactional
    public List<Link> getAllLinks() {
        daoFacade.getList("select s from Skill s left outer join fetch s.collaborators");
        daoFacade.getList("select l from Link l left outer join fetch l.skill1");
        return daoFacade.getList("select l from Link l left outer join fetch l.skill2");
    }
}
