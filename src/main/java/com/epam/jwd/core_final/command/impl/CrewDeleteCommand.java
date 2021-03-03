package com.epam.jwd.core_final.command.impl;

import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.io.IOException;

public class CrewDeleteCommand implements Command {
    ApplicationContext context;
    CrewServiceImpl service;
    CrewMember member;

    public CrewDeleteCommand(CrewMember member, ApplicationContext context) {
        this.context=context;
        service = CrewServiceImpl.getInstance(context);
        this.member = member;
    }

    @Override
    public Object execute() {
        try {
            service.deleteCrewMember(member);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
