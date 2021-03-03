package com.epam.jwd.core_final;


import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;

import com.epam.jwd.core_final.exception.InvalidStateException;

public class Main {

    public static void main(String[] args) throws InvalidStateException {
        System.out.println("start");
       ApplicationMenu menu=Application.start();
       menu.run();

        System.out.println("end");







        //Application.start();
    }
}