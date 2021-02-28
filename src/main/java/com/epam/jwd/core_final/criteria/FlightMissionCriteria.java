package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;
    private Planet startPlanet;
    private Planet endPlanet;

    public FlightMissionCriteria(LocalDate startDate, LocalDate endDate, Long distance, Spaceship assignedSpaceShift, List<CrewMember> assignedCrew, MissionResult missionResult, Planet startPlanet, Planet endPlanet) {

        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.assignedSpaceShift = assignedSpaceShift;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
        this.startPlanet = startPlanet;
        this.endPlanet = endPlanet;
    }
}
