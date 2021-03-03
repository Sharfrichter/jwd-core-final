package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.BaseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface SaveInFileStrategy<T extends BaseEntity> {
    public void save(Path path, List<T>list) throws IOException;
}
