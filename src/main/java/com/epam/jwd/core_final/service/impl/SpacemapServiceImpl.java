package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class SpacemapServiceImpl implements SpacemapService {
    ApplicationContext context;

    public SpacemapServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Planet getRandomPlanet() {
        List<Planet> planets= (List<Planet>) context.retrieveBaseEntityList(Planet.class);
        return planets.get(new Random().nextInt(planets.size()));

    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        Long x1 = first.getX();
        Long x2 = second.getX();
        Long y1 = first.getY();
        Long y2 = second.getY();
        double distance=Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
        return (int) Math.round(distance);
    }
}
