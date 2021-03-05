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
        Timer timer = new Timer(true);
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



        System.out.println("end");
    }
}