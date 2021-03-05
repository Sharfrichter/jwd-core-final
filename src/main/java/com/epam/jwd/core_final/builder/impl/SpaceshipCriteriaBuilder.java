package com.epam.jwd.core_final.builder.impl;

import com.epam.jwd.core_final.builder.CriteriaBuilder;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.util.Logger;

import java.util.Map;

public class SpaceshipCriteriaBuilder extends CriteriaBuilder<SpaceshipCriteria> {
    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;

    @Override
    public SpaceshipCriteria build() {
        return new SpaceshipCriteria(crew, flightDistance, isReadyForNextMissions);
    }

    @Override
    public SpaceshipCriteriaBuilder add(Object value) {
        super.add(value);
        Logger.info("was created spaceship criteria " + crew + " " + flightDistance + " " + isReadyForNextMissions);
        return this;

    }
}
