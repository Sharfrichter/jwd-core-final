package com.epam.jwd.core_final.command.impl;

import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

public class MissionGetCommand implements Command {
    FlightMissionCriteria criteria;
    MissionService service;
    ApplicationContext context;
    public MissionGetCommand(ApplicationContext context,FlightMissionCriteria criteria) {
        this(context);
        this.criteria = criteria;
    }

    public MissionGetCommand(ApplicationContext context) {
        service = MissionServiceImpl.getInstance(context);
    }

    @Override
    public Object execute() {
        if(criteria==null){
            return service.findAllMissions();
        }else {
            return service.findAllMissionsByCriteria(criteria);
        }
    }
}
