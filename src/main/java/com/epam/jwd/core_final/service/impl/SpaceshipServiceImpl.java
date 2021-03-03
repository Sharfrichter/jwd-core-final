package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;

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
        return (List<Spaceship>) context.retrieveBaseEntityList(Spaceship.class);
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
        boolean exists=spaceships.stream().anyMatch(ship ->{
            return ship.getName().equals(spaceship.getName());
        });
        if(exists){
            throw new RuntimeException("this ship is already exists");
        }else {
            context.retrieveBaseEntityList(Spaceship.class).add(spaceship);
            return spaceship;
        }
    }
}
