package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.lang.reflect.Field;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    @Override
    public CrewMember create(Object... args) {
        CrewMember member=new CrewMember();
        for(Object obj:args){
            if(obj instanceof Rank){
                member.setRank((Rank) obj);
            }else if(obj instanceof Role){
                member.setRole((Role) obj);
            }else if(obj instanceof String){
                member.setName(obj.toString());
            }
        }
        return member;
    }
}
