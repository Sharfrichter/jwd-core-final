package com.epam.jwd.core_final.command.impl;

import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;

public class PlanetGetCommand implements Command {
    SpacemapService service;

    public PlanetGetCommand(ApplicationContext context) {
        service = SpacemapServiceImpl.getInstance(context);
    }

    @Override
    public Object execute() {
        return service.findAllPlanets();
        }
    }

