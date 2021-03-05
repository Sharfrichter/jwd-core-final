package com.epam.jwd.core_final.context;

// todo replace Object with your own types

import com.epam.jwd.core_final.builder.impl.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.builder.impl.SpaceshipCriteriaBuilder;
import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.command.impl.*;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.NavigationServiceImpl;
import com.epam.jwd.core_final.strategy.load.CrewFileLoader;
import com.epam.jwd.core_final.strategy.load.SpaceshipFileLoader;
import com.epam.jwd.core_final.util.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@FunctionalInterface
public interface ApplicationMenu {


    ApplicationContext getApplicationContext();

    default String printAvailableOptions(int depth) {
        if (depth == 0) {
            return "1-Crew\n2-Spaceships\n3-Missions\n";
        }
        if (depth == 1) {
            return "1-Create\n2-Get\n3-Get with criteria\n4-Delete";
        }
        if (depth == 2) {
            return "1-Create\n2-Start Mission\n3-Console info\n4-File info";
        }
        return null;
    }

    default Object handleUserInput(Command command) {
        return command.execute();
    }

    default void run() {
        Logger.info("UI has been started");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        int value = 1000;
        int option = 0;
        while (value != 0) {
            System.out.println(printAvailableOptions(0));
            value = scanner.nextInt();
            if (value == 0) {
                return;
            }
            switch (value) {
                case 1:
                    System.out.println(printAvailableOptions(1));
                    option = scanner.nextInt();
                    if (option == 1) {
                        List<Object> values = new ArrayList<>();
                        System.out.println("First name");
                        String name = scanner.next();
                        System.out.println("Second name");
                        String secondName = scanner.next();
                        String res = name + " " + secondName;
                        values.add(res);
                        System.out.println("Role");
                        values.add(Role.resolveRoleById(scanner.nextInt()));
                        System.out.println("Rank");
                        values.add(Rank.resolveRankById(scanner.nextInt()));
                        CrewCreateCommand command = new CrewCreateCommand(getApplicationContext(), values.toArray());
                        try {
                            handleUserInput(command);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (option == 2) {
                        CrewGetCommand command = new CrewGetCommand(getApplicationContext());
                        List<CrewMember> members = (List<CrewMember>) handleUserInput(command);
                        members.forEach(System.out::println);
                    } else if (option == 3) {
                        CrewMemberCriteriaBuilder builder = new CrewMemberCriteriaBuilder();
                        int val = 0;
                        System.out.println("Role");
                        val = scanner.nextInt();
                        if (val > 0) {
                            builder.add(Role.resolveRoleById(val));
                        }
                        System.out.println("Rank");
                        val = scanner.nextInt();
                        if (val > 0) {
                            builder.add(Rank.resolveRankById(val));
                        }
                        System.out.println("Ready for next");
                        String ready = scanner.next();
                        if (ready.contains("true")) {
                            builder.add(true);
                        } else if (ready.contains("false")) {
                            builder.add(false);
                        }
                        CrewMemberCriteria criteria = builder.build();
                        CrewGetCommand command = new CrewGetCommand(getApplicationContext(), criteria);
                        List<CrewMember> members = (List<CrewMember>) command.execute();
                        members.forEach(System.out::println);

                    } else if (option == 4) {
                        CrewFileLoader loader = new CrewFileLoader();
                        List<CrewMember> members = null;
                        Command command = new CrewGetCommand(getApplicationContext());
                        members = (List<CrewMember>) handleUserInput(command);
                        String name;
                        String surname;
                        System.out.println("Enter first name");
                        name = scanner.next();
                        System.out.println("Enter second name");
                        surname = scanner.next();
                        for (int i = 0; i < members.size(); i++) {
                            if (members.get(i).getName().equals(name + " " + surname)) {
                                command = new CrewDeleteCommand(members.get(i), getApplicationContext());
                                handleUserInput(command);
                                break;
                            }
                        }

                    }
                    continue;
                case 2:
                    System.out.println(printAvailableOptions(1));
                    option = scanner.nextInt();
                    if (option == 1) {
                        List<Object> values = new ArrayList<>();
                        Map<Role, Short> map = new HashMap<>();
                        System.out.println("Name");
                        String name = scanner.next();
                        values.add(name);
                        System.out.println("Distance");
                        values.add(scanner.nextLong());
                        System.out.println("count of " + Role.MISSION_SPECIALIST);
                        map.put(Role.MISSION_SPECIALIST, scanner.nextShort());
                        System.out.println("count of " + Role.FLIGHT_ENGINEER);
                        map.put(Role.FLIGHT_ENGINEER, scanner.nextShort());
                        System.out.println("count of " + Role.PILOT);
                        map.put(Role.PILOT, scanner.nextShort());
                        System.out.println("count of " + Role.COMMANDER);
                        map.put(Role.COMMANDER, scanner.nextShort());
                        values.add(map);
                        SpaceshipCreateCommand command = new SpaceshipCreateCommand(getApplicationContext(), values.toArray());
                        try {
                            handleUserInput(command);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (option == 2) {

                        Command command = new SpaceshipGetCommand(getApplicationContext());
                        List<Spaceship> spaceships = (List<Spaceship>) handleUserInput(command);
                        spaceships.forEach(System.out::println);

                    } else if (option == 3) {

                        SpaceshipCriteriaBuilder builder = new SpaceshipCriteriaBuilder();
                        Map<Role, Short> map = null;
                        long val = 0;
                        System.out.println("Distance");
                        val = scanner.nextLong();
                        if (val > 0) {
                            builder.add(val);
                        }
                        System.out.println("with crew?");
                        if (scanner.next().contains("y")) {
                            map = new HashMap<>();
                            System.out.println("count of " + Role.MISSION_SPECIALIST);
                            short tmp = scanner.nextShort();
                            map.put(Role.MISSION_SPECIALIST, tmp);
                            System.out.println("count of " + Role.FLIGHT_ENGINEER);
                            tmp = scanner.nextShort();
                            map.put(Role.FLIGHT_ENGINEER, tmp);
                            System.out.println("count of " + Role.PILOT);
                            tmp = scanner.nextShort();
                            map.put(Role.PILOT, tmp);
                            System.out.println("count of " + Role.COMMANDER);
                            tmp = scanner.nextShort();
                            map.put(Role.COMMANDER, tmp);
                        }
                        System.out.println("Ready for next");
                        String ready = scanner.next();
                        if (ready.contains("true")) {
                            builder.add(true);
                        } else if (ready.contains("false")) {
                            builder.add(false);
                        }
                        SpaceshipCriteria criteria = builder.build();
                        SpaceshipGetCommand command = new SpaceshipGetCommand(getApplicationContext(), criteria);
                        List<Spaceship> spaceships = (List<Spaceship>) command.execute();
                        spaceships.forEach(System.out::println);
                    } else if (option == 4) {
                        SpaceshipFileLoader loader = new SpaceshipFileLoader();
                        List<Spaceship> spaceships = null;
                        Command command = new SpaceshipGetCommand(getApplicationContext());
                        spaceships = (List<Spaceship>) handleUserInput(command);

                        System.out.println("Enter name");
                        String name = scanner.next();

                        for (int i = 0; i < spaceships.size(); i++) {
                            if (spaceships.get(i).getName().equals(name)) {
                                command = new SpaceshipDeleteCommand(spaceships.get(i), getApplicationContext());
                                handleUserInput(command);
                                break;
                            }
                        }
                    }
                    continue;
                case 3:
                    System.out.println(printAvailableOptions(2));
                    option = scanner.nextInt();
                    if (option == 1) {
                        Command command = new PlanetGetCommand(getApplicationContext());
                        FlightMissionFactory flightMissionFactory = new FlightMissionFactory();
                        List<Planet> planets = (List<Planet>) handleUserInput(command);
                        command = new SpaceshipGetCommand(getApplicationContext());
                        List<Spaceship> spaceships = (List<Spaceship>) handleUserInput(command);
                        System.out.println("Start planet name");
                        String startPlanetName = scanner.next();
                        Planet startPlanet = planets.stream().filter(planet -> {
                            return planet.getName().equals(startPlanetName);
                        }).findAny().get();
                        System.out.println("Destination planet name");
                        String destination = scanner.next();
                        Planet endPlanet = planets.stream()
                                .filter(planet -> planet.getName().equals(destination))
                                .findAny()
                                .get();
                        System.out.println("Start date");
                        String startDateLine = scanner.next();

                        LocalDateTime startDate = LocalDateTime.parse(startDateLine, DateTimeFormatter.ofPattern(ApplicationProperties.getDateTimeFormat()));

                        FlightMission mission = flightMissionFactory.create();
                        mission.setStartPlanet(startPlanet);
                        mission.setEndPlanet(endPlanet);
                        mission.setStartDate(startDate);
                        MissionServiceImpl.getInstance(getApplicationContext()).createMission(mission);


                    } else if (option == 2) {
                        Command command = new MissionGetCommand(getApplicationContext());
                        List<FlightMission> missions = (List<FlightMission>) handleUserInput(command);
                        missions = missions.stream().filter(mission -> mission.getMissionResult().equals(MissionResult.PLANNED)).collect(Collectors.toList());
                        for (int i = 0; i < missions.size(); i++) {
                            System.out.println(i + " " + missions.get(i));
                        }
                        System.out.println("choose number");
                        int id = scanner.nextInt();
                        FlightMission flightMission = missions.get(id);
                        command = new MissionStartCommand(flightMission, getApplicationContext());
                        final FlightMission mission = (FlightMission) handleUserInput(command);
                        Thread thread = new Thread(() -> {
                            try {
                                NavigationServiceImpl.getInstance(getApplicationContext()).navigate(mission);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        thread.start();

                    } else if (option == 3) {
                        Command command = new MissionGetCommand(getApplicationContext());
                        ((List<FlightMission>) handleUserInput(command)).forEach(System.out::println);
                    } else if (option == 4) {
                        Command command = new MissionSaveCommand(getApplicationContext());
                        handleUserInput(command);
                    }

                    continue;

                default:
                    return;
            }
        }
    }
}
