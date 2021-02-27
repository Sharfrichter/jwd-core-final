package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.BaseEntity;

import java.nio.file.Path;
import java.util.List;

public interface LoadFromFileStrategy<T extends BaseEntity> {
    List<? extends AbstractBaseEntity> load(Path path);
}
