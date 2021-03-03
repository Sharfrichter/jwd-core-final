package com.epam.jwd.core_final.command.impl;

import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

public class SpaceshipGetCommand implements Command {
    SpaceshipCriteria criteria;
    SpaceshipService service;
    ApplicationContext context;
    public SpaceshipGetCommand(ApplicationContext context, SpaceshipCriteria criteria) {
        this(context);
        this.criteria = criteria;
    }

    public SpaceshipGetCommand(ApplicationContext context) {
        service = SpaceshipServiceImpl.getInstance(context);
    }

    @Override
    public Object execute() {
        if(criteria==null){
            return service.findAllSpaceships();
        }else {
            return service.findAllSpaceshipsByCriteria(criteria);
        }
    }
}
