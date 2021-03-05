package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.service.NavigationService;
import com.epam.jwd.core_final.strategy.load.PlanetFileLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;

public class NavigationServiceImpl implements NavigationService {
    private static NavigationServiceImpl instance;
    private ApplicationContext context;
    private List<Planet> optimalWay = new ArrayList<>();
    private Long optimalDistance = 100000000000000L;
    private Graph graph;

    private NavigationServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    public static NavigationServiceImpl getInstance(ApplicationContext context) {
        if (instance == null) {
            instance = new NavigationServiceImpl(context);
        } else {
            instance.context = context;
        }

        return instance;
    }

    public List<Planet> getOptimalWay() {
        return optimalWay;
    }


    @Override
    public Long navigate(FlightMission mission) throws IOException {
        Long optimalDistance = 0L;
        initGraph(mission.getAssignedSpaceShip().getFlightDistance());
        Planet begin = mission.getStartPlanet();
        Planet destination = mission.getEndPlanet();
        Node beg = null;
        Node dest = null;
        for (Node node : graph.nodes) {
            if (node.planet.equals(begin)) {
                beg = node;
            } else if (node.planet.equals(destination)) {
                dest = node;
            }
        }
        Stack<Node> queue = new Stack<>();
        queue.add(beg);
        beg.visited = true;
        int counter = 1000000;
        while (!queue.isEmpty()) {
            Node next = queue.pop();
            for (Node neighbor : next.next) {
                if (!neighbor.visited) {
                    counter = Math.min(counter, SpacemapServiceImpl.getInstance(context).getDistanceBetweenPlanets(neighbor.planet, next.planet));
                    queue.add(neighbor);
                    neighbor.visited = true;
                    if (neighbor.planet.equals(destination)) {
                        optimalDistance += SpacemapServiceImpl.getInstance(context).getDistanceBetweenPlanets(next.planet, neighbor.planet);
                        try {
                            Thread.sleep(optimalDistance*1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mission.setMissionResult(MissionResult.COMPLETED);
                        mission.getAssignedSpaceShip().setReadyForNextMissions(true);
                        mission.setEndDate(mission.getStartDate().plusSeconds(optimalDistance));
                        for(CrewMember member:mission.getAssignedCrew()){
                            member.setReadyForNextMissions(true);
                        }
                        mission.setDistance(optimalDistance);
                        return optimalDistance;
                    }

                }
            }
            optimalDistance+=counter;
        }
        mission.setMissionResult(MissionResult.FAILED);
        return 0L;
    }




    public void initGraph(Long maxDistance) throws IOException {
        graph = new Graph();
        PlanetFileLoader loader = new PlanetFileLoader();
        List<Planet> planets = loader.load(Path.of("src/main/resources/" + ApplicationProperties.getInputRootDir() + "/" + ApplicationProperties.getPlanetsFileName()));
        List<Node> nodes = new ArrayList<>();
        graph.nodes = nodes;
        for (Planet planet : planets) {
            Node node = new Node();
            node.planet = planet;
            nodes.add(node);
        }
        for(int i=0;i<nodes.size();i++){
            for(int j=0;j<nodes.size();j++){
             if(i!=j&&SpacemapServiceImpl.getInstance(context).getDistanceBetweenPlanets(nodes.get(i).planet,nodes.get(j).planet)<=maxDistance){
                 nodes.get(i).next.add(nodes.get(j));
                 nodes.get(j).next.add(nodes.get(i));
             }
            }
        }

    }

    public class Node {
        Planet planet;
        ArrayList<Node> next = new ArrayList<>();
        boolean visited = false;
    }

    public class Graph {
        List<Node> nodes;
    }
}
