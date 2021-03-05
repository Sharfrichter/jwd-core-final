package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 * from {@link Planet}
 * to {@link Planet}
 */
public class FlightMission extends AbstractBaseEntity {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    Long distance;
    Spaceship assignedSpaceShip;
    List<CrewMember> assignedCrew;
    MissionResult missionResult;
    Planet startPlanet;
    Planet endPlanet;

    public FlightMission() {
        super();
        assignedCrew = new ArrayList<>();
        missionResult=MissionResult.PLANNED;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public void setAssignedSpaceShip(Spaceship assignedSpaceShip) {
        this.assignedSpaceShip = assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    public Planet getStartPlanet() {
        return startPlanet;
    }

    public void setStartPlanet(Planet startPlanet) {
        this.startPlanet = startPlanet;
    }

    public Planet getEndPlanet() {
        return endPlanet;
    }

    public void setEndPlanet(Planet endPlanet) {
        this.endPlanet = endPlanet;
    }

    @Override
    public String toString() {
        return "id: "+getId()+" "+
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", distance=" + distance +
                ", assignedSpaceShip=" + assignedSpaceShip +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                ", startPlanet=" + startPlanet +
                ", endPlanet=" + endPlanet +
                '}';
    }
}
