package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {
    private ApplicationContext applicationContext;
    private static MissionServiceImpl instance;

    public static MissionServiceImpl getInstance(ApplicationContext context) {
        if(instance==null){
            instance = new MissionServiceImpl(context);
        }else {
            instance.applicationContext = context;
        }
        return instance;
    }

    private MissionServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return (List<FlightMission>) applicationContext.retrieveBaseEntityList(FlightMission.class);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        List<FlightMission> missions = findAllMissions();
        CriteriaServiceImpl criteriaService = new CriteriaServiceImpl(criteria);
        return missions.stream().filter(criteriaService::isSuitable).collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        List<FlightMission> missions = findAllMissionsByCriteria(criteria);
        return missions.stream().findAny();
    }

    @Override
    public FlightMission updateMissionDetails(FlightMission flightMission) {
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        applicationContext.retrieveBaseEntityList(FlightMission.class).add(flightMission);
        return flightMission;
    }
}
