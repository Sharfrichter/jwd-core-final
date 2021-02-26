package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.strategy.impl.CrewFileLoader;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CrewFileLoader loader = new CrewFileLoader();
        List<CrewMember> crewMembers= (List<CrewMember>) loader.load(Path.of("src/main/resources/input/crew"));
        System.out.println("1");



        //Application.start();
    }
}