package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.impl.NavigationServiceImpl;

import java.io.IOException;
import java.util.List;

public interface NavigationService {
    int navigate(Planet begin, Planet destination) throws IOException;
}
