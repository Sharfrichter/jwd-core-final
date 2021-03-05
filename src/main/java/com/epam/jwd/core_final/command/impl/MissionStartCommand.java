package com.epam.jwd.core_final.command.impl;

import com.epam.jwd.core_final.command.Command;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

public class MissionStartCommand implements Command {
    private FlightMission mission;
    private MissionServiceImpl missionService;
    private ApplicationContext context;

    public MissionStartCommand(FlightMission mission, ApplicationContext context) {
        this.mission = mission;
        this.context = context;
        this.missionService = MissionServiceImpl.getInstance(context);
    }

    @Override
    public Object execute() {
        mission.setMissionResult(MissionResult.IN_PROGRESS);
        missionService.assignSpaceshipOnMission(mission);
        missionService.assignCrewMemberOnMission(mission);
        return mission;
    }
}
