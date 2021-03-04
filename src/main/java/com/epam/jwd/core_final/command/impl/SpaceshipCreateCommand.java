package com.epam.jwd.core_final.command.impl;

import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

public class SpaceshipCreateCommand implements Command {
    ApplicationContext context;
    Spaceship spaceship;
    SpaceshipService service;

    public SpaceshipCreateCommand(ApplicationContext context,Object... args) {
        SpaceshipFactory factory = new SpaceshipFactory();
        this.spaceship = factory.create(args);
        this.context = context;
        this.service = SpaceshipServiceImpl.getInstance(context);
    }

    @Override
    public Object execute() {
        return service.createSpaceship(this.spaceship);
    }
}
