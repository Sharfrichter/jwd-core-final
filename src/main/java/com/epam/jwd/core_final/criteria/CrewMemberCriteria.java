package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private String name;
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    public CrewMemberCriteria(String name,Role role, Rank rank, Boolean isReadyForNextMissions) {
        this.name = name;
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMissions = isReadyForNextMissions;
    }
}
