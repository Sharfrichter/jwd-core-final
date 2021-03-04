package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.strategy.load.SpaceshipFileLoader;
import com.epam.jwd.core_final.strategy.save.SpaceshipFileSaver;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {
    private ApplicationContext context;
    private static SpaceshipServiceImpl instance;
    private SpaceshipServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    public static SpaceshipServiceImpl getInstance(ApplicationContext context) {
        if(instance==null){
            instance = new SpaceshipServiceImpl(context);
        }else {
            instance.context = context;
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        SpaceshipFileLoader loader = new SpaceshipFileLoader();
        try {
            return loader.load(Path.of("src/main/resources/" + ApplicationProperties.getInputRootDir() + "/" + ApplicationProperties.getSpaceshipsFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> spaceships = findAllSpaceships();
        CriteriaServiceImpl criteriaService = new CriteriaServiceImpl(criteria);
        return spaceships.stream().filter(criteriaService::isSuitable).collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> spaceships = findAllSpaceshipsByCriteria(criteria);
        return spaceships.stream().findAny();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return spaceship;
    }

    //todo
    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {

    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        List<Spaceship> spaceships = findAllSpaceships();
        boolean exists=spaceships.stream().anyMatch(ship -> ship.getName().equals(spaceship.getName()));
        if(exists){
            throw new RuntimeException("this ship is already exists");
        }else {
            SpaceshipFileSaver saver = new SpaceshipFileSaver();
            spaceships.add(spaceship);
            try {
                saver.save(Path.of("src/main/resources/"+ ApplicationProperties.getInputRootDir()+"/"+ApplicationProperties.getSpaceshipsFileName()),spaceships);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return spaceship;
        }

    }

    @Override
    public void deleteSpaceship(Spaceship spaceship) {
        spaceship.setValid(false);
        List<Spaceship> spaceships = findAllSpaceships();
        spaceships.removeIf(ship -> ship.getName().equals(spaceship.getName()));
        SpaceshipFileSaver saver = new SpaceshipFileSaver();
        try {
            saver.save(Path.of("src/main/resources/"+ ApplicationProperties.getInputRootDir()+"/"+ApplicationProperties.getSpaceshipsFileName()),spaceships);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
