package com.epam.jwd.core_final;


import com.epam.jwd.core_final.builder.impl.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        NassaContext context = new NassaContext();
        try {
            context.init();
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
        SpacemapService spacemapService = new SpacemapServiceImpl(context);
        Planet planet=spacemapService.getRandomPlanet();

        CrewServiceImpl crewService = new CrewServiceImpl(context);
        CrewMemberCriteriaBuilder builder = new CrewMemberCriteriaBuilder();
        CrewMemberCriteria criteria=builder.add(Role.FLIGHT_ENGINEER).build();
        List<CrewMember> crew=crewService.findAllCrewMembersByCriteria(criteria);
        CrewMemberFactory factory = new CrewMemberFactory();


        crewService.createCrewMember(factory.create("Davey Btley"));
        System.out.println("1");







        //Application.start();
    }
}