package com.epam.jwd.core_final.domain;

import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    private boolean isValid;
    private Map<Role,Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;

    public Spaceship() {
        super();
        isReadyForNextMissions = true;
        isValid=true;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return Objects.equals(crew, spaceship.crew) &&
                Objects.equals(flightDistance, spaceship.flightDistance) &&
                Objects.equals(isReadyForNextMissions, spaceship.isReadyForNextMissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crew, flightDistance, isReadyForNextMissions);
    }

    @Override
    public String toString() {
        return getName()+" "+ crew + " "+flightDistance +
                " isReadyForNextMissions=" + isReadyForNextMissions;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
