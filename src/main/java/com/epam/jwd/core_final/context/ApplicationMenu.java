package com.epam.jwd.core_final.context;

// todo replace Object with your own types

import com.epam.jwd.core_final.command.Command;

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
            return "1-Update\n2-Create\n3-Get\n4-Get with criteria";
        }
        return null;
    }

    default Object handleUserInput(Command command) {
        return command.execute();
    }

    default void run(){
        Scanner scanner = new Scanner(System.in);
        int value=1000;
        while (value!=0){
            System.out.println(printAvailableOptions(0));
            value = scanner.nextInt();
        }
    }
}
