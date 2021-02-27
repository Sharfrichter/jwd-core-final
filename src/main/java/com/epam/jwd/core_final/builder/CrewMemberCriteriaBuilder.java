package com.epam.jwd.core_final.builder;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.lang.reflect.Field;

public class CrewMemberCriteriaBuilder implements CriteriaBuilder<CrewMemberCriteria> {
    private String name;
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    @Override
    public CrewMemberCriteriaBuilder add(Object value) {
        Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.getType().equals(value.getClass())){
                try {
                    field.set(this,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    @Override
    public CrewMemberCriteria build() {
        return new CrewMemberCriteria(name,role,rank,isReadyForNextMissions);
    }
}
