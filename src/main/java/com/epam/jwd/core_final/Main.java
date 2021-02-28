package com.epam.jwd.core_final;

import com.epam.jwd.core_final.builder.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.strategy.impl.CrewFileLoader;
import com.epam.jwd.core_final.strategy.impl.PlanetFileLoader;
import com.epam.jwd.core_final.strategy.impl.SpaceshipFileLoader;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PlanetFileLoader loader = new PlanetFileLoader();
        List<Planet> p=loader.load(Path.of(ApplicationProperties.inputRootDir+ApplicationProperties.planetsFileName));
        System.out.println("1");




        //Application.start();
    }
}