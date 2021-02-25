package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;

import java.nio.file.Path;
import java.util.List;

public interface LoadFromFileStrategy {
    List<? extends AbstractBaseEntity> load(Path path);
}
