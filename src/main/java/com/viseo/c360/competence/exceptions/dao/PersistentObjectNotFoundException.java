package com.viseo.c360.competence.exceptions.dao;

public class PersistentObjectNotFoundException extends Exception{
    public PersistentObjectNotFoundException(){}
    public PersistentObjectNotFoundException(long id, Class type){
        super("No one "+type.getName()+" with id : '"+Long.toString(id)+"' has been found in database.");
    }
}
