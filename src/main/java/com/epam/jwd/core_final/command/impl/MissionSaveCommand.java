package com.epam.jwd.core_final.command.impl;

import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.strategy.save.MissionJSONSaver;

import java.io.IOException;
import java.nio.file.Path;

public class MissionSaveCommand implements Command {
    private ApplicationContext context;
    private MissionJSONSaver saver;

    public MissionSaveCommand(ApplicationContext context) {
        this.context = context;
        saver = new MissionJSONSaver(context);
    }

    @Override
    public Object execute() {
        try {
            saver.save(Path.of("src/main/resources/"
                    + ApplicationProperties.getOutputRootDir()
                    + "/" + ApplicationProperties.getMissionsFileName()
                    + ".json"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
