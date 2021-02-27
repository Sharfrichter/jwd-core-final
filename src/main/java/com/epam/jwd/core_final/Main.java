package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.strategy.impl.CrewFileLoader;
import com.epam.jwd.core_final.strategy.impl.SpaceshipFileLoader;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CrewFileLoader loader = new CrewFileLoader();
        SpaceshipFileLoader spaceshipFileLoader = new SpaceshipFileLoader();

        List<Spaceship> ships=spaceshipFileLoader.load(Path.of(ApplicationProperties.inputRootDir+ApplicationProperties.spaceshipsFileName));
        System.out.println("1");
        //List<CrewMember> crewMembers=loader.load(Path.of("src/main/resources/input/crew"));




        //Application.start();
    }
}