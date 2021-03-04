package com.epam.jwd.core_final.command.impl;

import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

public class SpaceshipDeleteCommand implements Command {
    ApplicationContext context;
    SpaceshipService service;
    Spaceship spaceship;

    public SpaceshipDeleteCommand(Spaceship spaceship, ApplicationContext context) {
        this.context=context;
        service = SpaceshipServiceImpl.getInstance(context);
        this.spaceship = spaceship;
    }
    @Override
    public Object execute() {
        service.deleteSpaceship(spaceship);
        return true;
    }
}
