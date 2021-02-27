package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    @Override
    public Spaceship create(Object... args) {
        Spaceship spaceship = new Spaceship();
        for (Object obj : args) {
            if (obj instanceof String) {
                spaceship.setName((String) obj);
            } else if (obj instanceof Map) {
                spaceship.setCrew((Map<Role, Short>) obj);
            } else if (obj instanceof Long) {
                spaceship.setFlightDistance((Long) obj);
            }
        }
        return spaceship;
    }
}
