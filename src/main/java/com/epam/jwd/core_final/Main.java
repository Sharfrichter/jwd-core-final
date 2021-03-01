package com.epam.jwd.core_final;


import com.epam.jwd.core_final.builder.impl.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.command.CrewGetCommand;
import com.epam.jwd.core_final.command.CrewUpdateCommand;
import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.context.impl.NassaContext;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;

import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws InvalidStateException {
        System.out.println("start");
        ApplicationMenu menu=Application.start();

        CrewMemberCriteriaBuilder criteriaBuilder = new CrewMemberCriteriaBuilder();
        Command command = new CrewGetCommand(menu.getApplicationContext(),criteriaBuilder.add(Role.FLIGHT_ENGINEER).build());
        List<CrewMember> members = (List<CrewMember>) menu.handleUserInput(command);

        menu.run();

        System.out.println("end");







        //Application.start();
    }
}