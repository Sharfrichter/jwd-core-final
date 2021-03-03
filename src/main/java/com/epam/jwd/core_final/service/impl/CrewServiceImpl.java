package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.strategy.load.CrewFileLoader;
import com.epam.jwd.core_final.strategy.save.CrewFileSaver;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
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
        CrewFileLoader loader = new CrewFileLoader();
        List<CrewMember> members = null;
        try {
            members=loader.load(Path.of("src/main/resources/"+ ApplicationProperties.getInputRootDir()+"/"+ApplicationProperties.getCrewFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
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

    public void deleteCrewMember(CrewMember crewMember) throws IOException {
        crewMember.setValid(false);
        CrewFileSaver saver = new CrewFileSaver();
        List<CrewMember> members = findAllCrewMembers();
        members.removeIf(member -> member.getName().equals(crewMember.getName()));
        saver.save(Path.of("src/main/resources/"+ ApplicationProperties.getInputRootDir()+"/"+ApplicationProperties.getCrewFileName()),members);
    }
}
