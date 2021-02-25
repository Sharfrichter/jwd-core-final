package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.domain.Rank;

public class Main {

    public static void main(String[] args) {
        Rank rank=Rank.resolveRankById(2);
        System.out.println(rank.getName());
        //Application.start();
    }
}