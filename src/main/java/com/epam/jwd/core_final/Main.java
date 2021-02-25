package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;

public class Main {

    public static void main(String[] args) {
        CrewMemberFactory memberFactory = new CrewMemberFactory();
        CrewMember member = memberFactory.create(Role.COMMANDER, Rank.CAPTAIN, "John Wick");
        System.out.println("1");


        //Application.start();
    }
}