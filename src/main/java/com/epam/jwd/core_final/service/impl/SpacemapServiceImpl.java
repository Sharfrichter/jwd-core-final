package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.strategy.load.PlanetFileLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class SpacemapServiceImpl implements SpacemapService {
    private ApplicationContext context;
    private static SpacemapServiceImpl instance;


    private SpacemapServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    public static SpacemapService getInstance(ApplicationContext context) {
        if(instance==null){
            instance=new SpacemapServiceImpl(context);
        }else {
            instance.context=context;
        }
        return instance;
    }

    @Override
    public Planet getRandomPlanet() {
        PlanetFileLoader loader = new PlanetFileLoader();
        List<Planet> planets=new ArrayList<>();
        try {
           planets= loader.load(Path.of("src/main/resources/"+ ApplicationProperties.getInputRootDir()+"/"+ApplicationProperties.getPlanetsFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public List<Planet> findAllPlanets() {
        PlanetFileLoader loader = new PlanetFileLoader();
        try {
            return loader.load(Path.of("src/main/resources/" + ApplicationProperties.getInputRootDir() + "/" + ApplicationProperties.getPlanetsFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
