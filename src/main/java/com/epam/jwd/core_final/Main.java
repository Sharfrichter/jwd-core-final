package com.epam.jwd.core_final;


import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.NavigationServiceImpl;
import com.epam.jwd.core_final.strategy.load.SpaceshipFileLoader;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InvalidStateException, IOException {
        System.out.println("start");
        ApplicationMenu menu = Application.start();
        NavigationServiceImpl service = NavigationServiceImpl.getInstance(menu.getApplicationContext());
        FlightMission mission = new FlightMission();
        Spaceship spaceship = new Spaceship();
        spaceship.setFlightDistance(1000L);
        mission.setAssignedSpaceShip(spaceship);
        List<Planet> planets = (List<Planet>) menu.getApplicationContext().retrieveBaseEntityList(Planet.class);
        /*mission.setStartPlanet(planets.get(0));
        mission.setEndPlanet(planets.get(3));*/


        service.initGraph(15L);
        System.out.println(service.navigate(planets.get(0), planets.get(3)));


        System.out.println("end");
        /*Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    menu.getApplicationContext().init();
                } catch (InvalidStateException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask, 1000, ApplicationProperties.getFileRefreshRate() * 1000);

        menu.run();
        SpaceshipFileLoader loader = new SpaceshipFileLoader();


        System.out.println("end");*/



        //Application.start();
    }
}