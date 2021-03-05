package com.epam.jwd.core_final.strategy.save;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.strategy.SaveInFileStrategy;
import com.epam.jwd.core_final.util.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class MissionJSONSaver {
    private ApplicationContext applicationContext;

    public MissionJSONSaver(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public void save(Path path, FlightMission mission) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<FlightMission> missions = (List<FlightMission>) applicationContext.retrieveBaseEntityList(FlightMission.class);
        mapper.writeValue(path.toFile(),mission);
        mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mission);

        Logger.info("Mission was saved");
    }
}
