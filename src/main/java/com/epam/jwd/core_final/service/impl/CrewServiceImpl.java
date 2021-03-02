package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.CrewService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {
    private ApplicationContext applicationContext;
    private static CrewServiceImpl instance;

    public static CrewServiceImpl getInstance(ApplicationContext context) {
        if(instance==null){
            instance = new CrewServiceImpl(context);
        }else {
            instance.applicationContext = context;
        }
        return instance;
    }

    private CrewServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return (List<CrewMember>) applicationContext.retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> members = findAllCrewMembers();
        CriteriaServiceImpl criteriaService = new CriteriaServiceImpl(criteria);
        return members.stream().filter(criteriaService::isSuitable).collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> members = findAllCrewMembersByCriteria(criteria);
        return members.stream().findAny();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        return crewMember;
    }


    //todo
    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        if(!crewMember.getReadyForNextMissions()){
            throw new RuntimeException("This crew member is not available");
        }else {
            crewMember.setReadyForNextMissions(false);
        }

    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        List<CrewMember> members = findAllCrewMembers();
        boolean exists=members.stream().anyMatch(member ->{
           if(member.getName().equals(crewMember.getName())){
               return true;
           }
           return false;
        });
        if(exists){
            throw new RuntimeException("this member is already exists");
        }else {
            applicationContext.retrieveBaseEntityList(CrewMember.class).add(crewMember);
            return crewMember;
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
