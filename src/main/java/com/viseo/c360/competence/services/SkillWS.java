package com.viseo.c360.competence.services;

import com.viseo.c360.competence.converters.skill.DescriptionToSkill;
import com.viseo.c360.competence.converters.skill.SkillToDescription;
import com.viseo.c360.competence.dao.SkillDAO;
import com.viseo.c360.competence.domain.skill.Skill;
import com.viseo.c360.competence.dto.skill.SkillDescription;
import com.viseo.c360.competence.exceptions.C360Exception;
import com.viseo.c360.competence.exceptions.dao.UniqueFieldException;
import com.viseo.c360.competence.exceptions.dao.util.ExceptionUtil;
import com.viseo.c360.competence.exceptions.dao.util.UniqueFieldErrors;
import org.springframework.core.convert.ConversionException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by HBO3676 on 15/06/2017.
 */
public class SkillWS {
    @Inject
    SkillDAO skillDAO;

    @Inject
    ExceptionUtil exceptionUtil;


    @RequestMapping(value = "${endpoint.addskill}", method = RequestMethod.POST)
    @ResponseBody
    public SkillDescription addSkill(@RequestBody SkillDescription skillDescription){
        try {
            Skill skill = skillDAO.addSkill(new DescriptionToSkill().convert(skillDescription));
            return new SkillToDescription().convert(skill);
        } catch (PersistenceException pe) {
            UniqueFieldErrors uniqueFieldErrors = exceptionUtil.getUniqueFieldError(pe);
            if (uniqueFieldErrors == null) throw new C360Exception(pe);
            else throw new UniqueFieldException(uniqueFieldErrors.getField());
        }
    }

    @RequestMapping(value = "${endpoint.addlink}", method = RequestMethod.POST)
    @ResponseBody
    public SkillDescription addLink(@RequestBody SkillDescription skillDescription, @RequestParam SkillDescription link){
        try {
            Skill skill = skillDAO.addLink(new DescriptionToSkill().convert(skillDescription),new DescriptionToSkill().convert(link));
            return new SkillToDescription().convert(skill);
        } catch (PersistenceException pe) {
            UniqueFieldErrors uniqueFieldErrors = exceptionUtil.getUniqueFieldError(pe);
            if (uniqueFieldErrors == null) throw new C360Exception(pe);
            else throw new UniqueFieldException(uniqueFieldErrors.getField());
        }
    }

    @RequestMapping(value = "${endpoint.updateskill}", method = RequestMethod.PUT)
    @ResponseBody
    public SkillDescription updateSkill(@RequestBody SkillDescription skillDescription){
        try {
            Skill skill = skillDAO.updateSkill(new DescriptionToSkill().convert(skillDescription));
            return new SkillToDescription().convert(skill);
        } catch (PersistenceException pe) {
            UniqueFieldErrors uniqueFieldErrors = exceptionUtil.getUniqueFieldError(pe);
            if (uniqueFieldErrors == null) throw new C360Exception(pe);
            else throw new UniqueFieldException(uniqueFieldErrors.getField());
        }
    }

    @RequestMapping(value = "${endpoint.removeskill}", method = RequestMethod.POST)
    @ResponseBody
    public SkillDescription removeSkill(@RequestBody SkillDescription skillDescription){
        try {
            Skill skill = skillDAO.removeSkill(new DescriptionToSkill().convert(skillDescription));
            return new SkillToDescription().convert(skill);
        } catch (PersistenceException pe) {
            UniqueFieldErrors uniqueFieldErrors = exceptionUtil.getUniqueFieldError(pe);
            if (uniqueFieldErrors == null) throw new C360Exception(pe);
            else throw new UniqueFieldException(uniqueFieldErrors.getField());
        }
    }

    @RequestMapping(value = "${endpoint.skills}", method = RequestMethod.GET)
    @ResponseBody
    public List<SkillDescription> getAllSkills() {
        try {
            return new SkillToDescription().convert(skillDAO.getAllSkills());
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new C360Exception(e);
        }
    }
}
