package com.epam.jwd.core_final.command;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

public class CrewUpdateCommand implements Command{
    ApplicationContext context;
    CrewServiceImpl service;
    CrewMember member;

    public CrewUpdateCommand(CrewMember member, ApplicationContext context) {
        this.context=context;
        service = CrewServiceImpl.getInstance(context);
        this.member = member;
    }

    @Override
    public CrewMember execute() {
        return service.updateCrewMemberDetails(member);
    }
}
