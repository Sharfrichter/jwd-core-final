package com.epam.jwd.core_final.builder.impl;

import com.epam.jwd.core_final.builder.CriteriaBuilder;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.lang.reflect.Field;

public class CrewMemberCriteriaBuilder extends CriteriaBuilder<CrewMemberCriteria> {
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;



    @Override
    public CrewMemberCriteria build() {
        return new CrewMemberCriteria(role,rank,isReadyForNextMissions);
    }
}
