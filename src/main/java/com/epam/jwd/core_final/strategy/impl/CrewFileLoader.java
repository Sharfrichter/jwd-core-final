package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.strategy.LoadFromFileStrategy;

import java.nio.file.Path;
import java.util.List;

public class CrewFileLoader implements LoadFromFileStrategy {
    @Override
    public List<? extends AbstractBaseEntity> load(Path path) {
        return null;
    }
}
