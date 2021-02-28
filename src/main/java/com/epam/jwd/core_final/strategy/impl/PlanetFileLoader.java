package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.strategy.LoadFromFileStrategy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

public class PlanetFileLoader implements LoadFromFileStrategy<Planet> {

    @Override
    public List<Planet> load(Path path) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
