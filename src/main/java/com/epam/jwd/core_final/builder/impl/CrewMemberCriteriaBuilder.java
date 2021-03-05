package com.epam.jwd.core_final.builder.impl;

import com.epam.jwd.core_final.builder.CriteriaBuilder;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.util.Logger;

public class CrewMemberCriteriaBuilder extends CriteriaBuilder<CrewMemberCriteria> {
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    @Override
    public CrewMemberCriteriaBuilder add(Object value) {
        super.add(value);
        return this;
    }


    @Override
    public CrewMemberCriteria build() {
        Logger.info("was created criteria " + role + " " + rank + " " + isReadyForNextMissions);
        return new CrewMemberCriteria(role, rank, isReadyForNextMissions);
    }
}
