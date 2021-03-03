package com.epam.jwd.core_final.context;

// todo replace Object with your own types

import com.epam.jwd.core_final.builder.impl.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.command.CrewCreateCommand;
import com.epam.jwd.core_final.command.CrewGetCommand;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@FunctionalInterface
public interface ApplicationMenu {


    ApplicationContext getApplicationContext();

    default String printAvailableOptions(int depth) {
        if(depth==0){
            return "1-Crew\n2-Spaceships\n3-Planets\n4-Missions\n";
        }if(depth==1){
            return "1-Create\n2-Get\n3-Get with criteria\n4-Delete";
        }
        return null;
    }

    default Object handleUserInput(Command command) {
        return command.execute();
    }

    default void run(){
        Scanner scanner = new Scanner(System.in);
        int value=1000;
        int option=0;
        while (value!=0){
            System.out.println(printAvailableOptions(0));
            value = scanner.nextInt();
            System.out.println(printAvailableOptions(1));
            option = scanner.nextInt();
            switch (value) {
                case 1:
                    if(option==1){
                        List<Object> values = new ArrayList<>();
                        System.out.println("Name");
                        values.add(scanner.next());
                        System.out.println("Role");
                        values.add(Role.resolveRoleById(scanner.nextInt()));
                        System.out.println("Rank");
                        values.add(Rank.resolveRankById(scanner.nextInt()));
                        CrewCreateCommand command = new CrewCreateCommand(getApplicationContext(), values);
                        handleUserInput(command);
                    }else if(option==2){
                        CrewGetCommand command = new CrewGetCommand(getApplicationContext());
                        List<CrewMember> members= (List<CrewMember>) handleUserInput(command);
                        members.forEach(System.out::println);
                    }else if(option==3){
                        CrewMemberCriteriaBuilder builder = new CrewMemberCriteriaBuilder();
                        int val=0;
                        System.out.println("Role");
                        val = scanner.nextInt();
                        if(val>0){
                            builder.add(Role.resolveRoleById(val));
                        }
                        System.out.println("Rank");
                        val=scanner.nextInt();
                        if(val>0){
                            builder.add(Rank.resolveRankById(val));
                        }
                        System.out.println("Ready for next");
                        String ready = scanner.next();
                        if (ready.contains("true")){
                            builder.add(true);
                        }else if(ready.contains("false")){
                            builder.add(false);
                        }
                        CrewMemberCriteria criteria = builder.build();
                        CrewGetCommand command = new CrewGetCommand(getApplicationContext(), criteria);
                        List<CrewMember> members = (List<CrewMember>) command.execute();
                        members.forEach(System.out::println);

                    }

            }
        }
    }
}
