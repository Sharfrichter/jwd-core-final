package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {
        if (id == 1) {
            return "MISSION_SPECIALIST";
        } else if (id == 2) {
            return "FLIGHT_ENGINEER";
        } else if (id == 3) {
            return "PILOT";
        } else if (id == 4) {
            return "COMMANDER";
        }
        return "unknown";
    }

    /**
     * todo via java.lang.enum methods!
     * @throws UnknownEntityException if such id does not exist
     */
    public static Role resolveRoleById(int id) {
        switch (id){
            case 1:
                return MISSION_SPECIALIST;
            case 2:
                return FLIGHT_ENGINEER;
            case 3:
                return PILOT;
            case 4:
                return COMMANDER;
            default:
                throw new UnknownEntityException("there is no entity with such id");
        }

    }
}
