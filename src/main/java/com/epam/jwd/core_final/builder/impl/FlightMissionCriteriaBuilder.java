package com.epam.jwd.core_final.builder.impl;

import com.epam.jwd.core_final.builder.CriteriaBuilder;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;

import java.time.LocalDate;
import java.util.List;

public class FlightMissionCriteriaBuilder extends CriteriaBuilder<FlightMissionCriteria>{
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;
    private Planet startPlanet;
    private Planet endPlanet;

    @Override
    public FlightMissionCriteriaBuilder add(Object value) {
        if(value.getClass().equals(LocalDate.class)||value.getClass().equals(Planet.class));
            else {
            super.add(value);
            }
        return this;
    }
    public FlightMissionCriteriaBuilder addStartDate(LocalDate date){
        this.startDate = date;
        return this;
    }

    public FlightMissionCriteriaBuilder addEndDate(LocalDate date){
        this.endDate = date;
        return this;
    }

    public FlightMissionCriteriaBuilder addStartPlanet(Planet planet){
        this.startPlanet = planet;
        return this;
    }

    public FlightMissionCriteriaBuilder addEndPlanet(Planet planet){
        this.endPlanet = planet;
        return this;
    }

    @Override
    public FlightMissionCriteria build() {
        return new FlightMissionCriteria(startDate,endDate,distance, assignedSpaceShip,assignedCrew,missionResult,startPlanet,endPlanet);
    }
}
