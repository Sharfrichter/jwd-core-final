package com.epam.jwd.core_final.command;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

public class CrewCreateCommand implements Command{
    ApplicationContext context;
    CrewMember member;
    CrewService service;

    public CrewCreateCommand(ApplicationContext context,Object...args) {
        CrewMemberFactory factory = new CrewMemberFactory();
        this.member = factory.create(args);
        this.context = context;
        this.service = CrewServiceImpl.getInstance(context);
    }

    @Override
    public Object execute() {
        return service.createCrewMember(member);
    }
}
