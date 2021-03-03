package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.strategy.LoadFromFileStrategy;
import com.epam.jwd.core_final.strategy.load.CrewFileLoader;
import com.epam.jwd.core_final.strategy.load.PlanetFileLoader;
import com.epam.jwd.core_final.strategy.load.SpaceshipFileLoader;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<Planet> planetMap = new ArrayList<>();
    private Collection<FlightMission> missions = new ArrayList<>();
    private LoadFromFileStrategy strategy;

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if(tClass.equals(CrewMember.class)){
            return (Collection<T>) crewMembers;
        }
        else if(tClass.equals(Spaceship.class)){
            return (Collection<T>) spaceships;
        }else if(tClass.equals(Planet.class)){
            return (Collection<T>) planetMap;
        }else if(tClass.equals(FlightMission.class)){
            return (Collection<T>) missions;
        }
        return null;
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        PropertyReaderUtil.loadProperties();
        strategy = new CrewFileLoader();
        try {
            crewMembers=strategy.load(Path.of("src/main/resources/"+ ApplicationProperties.getInputRootDir()+"/"+ApplicationProperties.getCrewFileName()));
        }catch (IOException e){
            throw new InvalidStateException("problem with crew file location property");
        }

        strategy = new PlanetFileLoader();
        try {
            planetMap = strategy.load(Path.of("src/main/resources/" + ApplicationProperties.getInputRootDir() + "/" + ApplicationProperties.getPlanetsFileName()));
        } catch (IOException e) {
            throw new InvalidStateException("Problem with planet file location property");
        }
        strategy = new SpaceshipFileLoader();
        try {
            spaceships = strategy.load(Path.of("src/main/resources/" + ApplicationProperties.getInputRootDir() + "/" + ApplicationProperties.getSpaceshipsFileName()));
        } catch (IOException e) {
            throw new InvalidStateException("Problem with spaceships file location property");
        }
    }
}
