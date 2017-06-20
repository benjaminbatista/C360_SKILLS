package com.viseo.c360.competence.services;


import com.viseo.c360.competence.converters.skill.DescriptionToLink;
import com.viseo.c360.competence.converters.skill.DescriptionToSkill;
import com.viseo.c360.competence.converters.skill.LinkToDescription;
import com.viseo.c360.competence.converters.skill.SkillToDescription;
import com.viseo.c360.competence.dao.LinkDAO;
import com.viseo.c360.competence.dao.SkillDAO;
import com.viseo.c360.competence.domain.skill.Link;
import com.viseo.c360.competence.dto.skill.LinkDescription;
import com.viseo.c360.competence.dto.skill.SkillDescription;
import com.viseo.c360.competence.exceptions.C360Exception;
import com.viseo.c360.competence.exceptions.dao.UniqueFieldException;
import com.viseo.c360.competence.exceptions.dao.util.ExceptionUtil;
import com.viseo.c360.competence.exceptions.dao.util.UniqueFieldErrors;
import org.springframework.core.convert.ConversionException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LinkWS {

    @Inject
    LinkDAO linkDAO;

    @Inject
    ExceptionUtil exceptionUtil;

    @CrossOrigin
    @RequestMapping(value = "${endpoint.addlink}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addLink(@RequestBody LinkDescription linkDescription){
        try {
            List<Link> links = linkDAO.getAllLinks();
            for(int i=0; i < links.size(); i++){
                if((links.get(i).getSkill1().getId() == linkDescription.getSkill1().getId() && links.get(i).getSkill2().getId() == linkDescription.getSkill2().getId()) || (links.get(i).getSkill1().getId() == linkDescription.getSkill2().getId() && links.get(i).getSkill2().getId() == linkDescription.getSkill1().getId())){
                    return false;
                }
            }
            linkDAO.addLink(new DescriptionToLink().convert(linkDescription));
            return true;
        } catch (PersistenceException pe) {
            UniqueFieldErrors uniqueFieldErrors = exceptionUtil.getUniqueFieldError(pe);
            if (uniqueFieldErrors == null) throw new C360Exception(pe);
            else throw new UniqueFieldException(uniqueFieldErrors.getField());
        }
    }


    @CrossOrigin
    @RequestMapping(value = "${endpoint.removelink}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean removeLink(@RequestBody LinkDescription linkDescription){
        try {
            linkDAO.removeLink(new DescriptionToLink().convert(linkDescription));
            return true;
        } catch (PersistenceException pe) {
            UniqueFieldErrors uniqueFieldErrors = exceptionUtil.getUniqueFieldError(pe);
            if (uniqueFieldErrors == null) throw new C360Exception(pe);
            else throw new UniqueFieldException(uniqueFieldErrors.getField());
        }
    }

    @CrossOrigin
    @RequestMapping(value = "${endpoint.links}", method = RequestMethod.GET)
    @ResponseBody
    public List<LinkDescription> getAllLinks() {
        try {
            return new LinkToDescription().convert(linkDAO.getAllLinks());
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new C360Exception(e);
        }
    }
}
