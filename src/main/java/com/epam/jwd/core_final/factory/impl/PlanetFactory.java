package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.EntityFactory;

public class PlanetFactory implements EntityFactory<Planet> {
    @Override
    public Planet create(Object... args) {
        Planet planet = new Planet();
        int counter=0;
        for(Object obj:args){
            if(obj instanceof String){
                planet.setName(obj.toString());
            }else {
                if (counter==0){
                    planet.setX((Long)obj);
                    counter++;
                }else {
                    planet.setY((Long)obj);
                }
            }
        }
        return planet;
    }


}
