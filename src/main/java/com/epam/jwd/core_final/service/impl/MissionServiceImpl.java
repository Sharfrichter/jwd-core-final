package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.builder.impl.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.builder.impl.SpaceshipCriteriaBuilder;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Override
    public void assignCrewMemberOnMission(FlightMission mission) throws RuntimeException{
        ArrayList<String> lockedCrew = new ArrayList<>();
        List<CrewMember> crewMembers = CrewServiceImpl.getInstance(applicationContext).findAllCrewMembers();
        for(FlightMission mission1:applicationContext.retrieveBaseEntityList(FlightMission.class)){
            if(!mission1.getMissionResult().equals(MissionResult.COMPLETED)){
                List<CrewMember> members = mission1.getAssignedCrew();
                for(CrewMember member:members){
                    lockedCrew.add(member.getName());
                }
            }
        }
        Short commanders;
        Short pilots;
        Short flightEnginers;
        Short missionSpecialists;
        Map<Role,Short> crew=mission.getAssignedSpaceShip().getCrew();
        commanders = crew.get(Role.COMMANDER);
        pilots = crew.get(Role.PILOT);
        flightEnginers = crew.get(Role.FLIGHT_ENGINEER);
        missionSpecialists = crew.get(Role.MISSION_SPECIALIST);

        for(CrewMember member:crewMembers){
            if(!lockedCrew.contains(member.getName())){
                if(member.getRole().equals(Role.COMMANDER)&&commanders>0){
                    mission.getAssignedCrew().add(member);
                    commanders--;
                }else if(member.getRole().equals(Role.PILOT)&&pilots>0){
                    mission.getAssignedCrew().add(member);
                    pilots--;
                }else if(member.getRole().equals(Role.FLIGHT_ENGINEER)&&flightEnginers>0){
                    mission.getAssignedCrew().add(member);
                    flightEnginers--;
                }else if(member.getRole().equals(Role.MISSION_SPECIALIST)&&missionSpecialists>0){
                    mission.getAssignedCrew().add(member);
                    missionSpecialists--;
                }
            }

        }

    }

    @Override
    public void assignSpaceshipOnMission(FlightMission mission) throws RuntimeException {
        ArrayList<String> lockedShips = new ArrayList<>();
        List<Spaceship> allShips = SpaceshipServiceImpl.getInstance(applicationContext).findAllSpaceships();
        for(FlightMission mission1:applicationContext.retrieveBaseEntityList(FlightMission.class)){
            if(!mission1.getMissionResult().equals(MissionResult.COMPLETED)&&mission1.getAssignedSpaceShip()!=null){
                lockedShips.add(mission1.getAssignedSpaceShip().getName());
            }
        }
        for(Spaceship spaceship:allShips){
            if(!lockedShips.contains(spaceship.getName())){
                mission.setAssignedSpaceShip(spaceship);
                spaceship.setReadyForNextMissions(false);
                return;
            }
        }


    }

}
