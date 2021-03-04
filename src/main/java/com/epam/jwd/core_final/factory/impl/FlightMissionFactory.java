package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FlightMissionFactory implements EntityFactory<FlightMission> {

    @Override

    public FlightMission create(Object... args) {
        int dateFlag=0;
        int planetFlag=0;
        FlightMission mission = new FlightMission();
        for(int i=0;i<args.length;i++){
            if(args[i] instanceof String){
                mission.setName(args[i].toString());
            }else if(args[i] instanceof LocalDate){
                if(dateFlag==0){
                    mission.setStartDate((LocalDateTime)args[i]);
                    dateFlag++;
                }else {
                    mission.setEndDate((LocalDateTime)args[i]);
                }
            }else if(args[i] instanceof Long){
                mission.setDistance((Long)args[i]);
            }else if(args[i] instanceof Spaceship){
                mission.setAssignedSpaceShip((Spaceship)args[i]);
            }else if(args[i] instanceof List){
                mission.setAssignedCrew((List<CrewMember>)args[i]);
            }else if(args[i] instanceof MissionResult){
                mission.setMissionResult((MissionResult)args[i]);
            }else if(args[i] instanceof Planet){
                if(planetFlag==0){
                    mission.setStartPlanet((Planet) args[i]);
                    planetFlag++;
                }else {
                    mission.setEndPlanet((Planet) args[i]);
                }
            }
        }
        return mission;
    }
}
