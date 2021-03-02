package com.epam.jwd.core_final.command;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

public class CrewGetCommand implements Command{
    CrewMemberCriteria criteria;
    CrewService service;
    ApplicationContext context;
    public CrewGetCommand(ApplicationContext context,CrewMemberCriteria criteria) {
        this(context);
        this.criteria = criteria;
    }

    public CrewGetCommand(ApplicationContext context) {
        service = CrewServiceImpl.getInstance(context);
    }

    @Override
    public Object execute() {
        if(criteria==null){
            return service.findAllCrewMembers();
        }else {
            return service.findAllCrewMembersByCriteria(criteria);
        }
    }
}
