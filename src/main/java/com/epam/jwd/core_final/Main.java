package com.epam.jwd.core_final;


import com.epam.jwd.core_final.builder.CriteriaBuilder;
import com.epam.jwd.core_final.builder.impl.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.builder.impl.FlightMissionCriteriaBuilder;
import com.epam.jwd.core_final.builder.impl.SpaceShipCriteriaBuilder;
import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.strategy.impl.CrewFileLoader;
import com.epam.jwd.core_final.strategy.impl.PlanetFileLoader;
import com.epam.jwd.core_final.strategy.impl.SpaceshipFileLoader;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        NassaContext context = new NassaContext();
        ApplicationProperties applicationProperties = new ApplicationProperties();
        PropertyReaderUtil.loadProperties();
        System.out.println(ApplicationProperties.getFileRefreshRate());





        //Application.start();
    }
}