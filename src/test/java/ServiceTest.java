import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {
    @Test
    public void planetServiceTest(){
        PlanetFactory factory = new PlanetFactory();
        Planet earth=factory.create("Earth",0L,0L);
        Planet test = new Planet();
        test.setName("Earth");
        test.setX(0L);
        test.setY(0L);
        assertEquals(factory.create("Earth",0L,0L),test);
        test.setY(0L);
        test.setX(10L);
        assertTrue(Math.abs(SpacemapServiceImpl.getInstance(null).getDistanceBetweenPlanets(earth,test)-10)<0.1);


    }
    @Test
    public void crewServiceTest(){
        PropertyReaderUtil.loadProperties();
        CrewService service = CrewServiceImpl.getInstance(null);
        List<CrewMember> members=service.findAllCrewMembers();
        CrewMember test = new CrewMemberFactory().create("Joseph Stalin", Rank.CAPTAIN, Role.COMMANDER);
        members.add(test);
        service.createCrewMember(test);
        List<CrewMember> newMembers = service.findAllCrewMembers();
        assertEquals(members.get(members.size()-1),newMembers.get(members.size()-1));
        members.remove(test);
        service.deleteCrewMember(test);
        newMembers=service.findAllCrewMembers();
        assertEquals(members.get(members.size()-1),newMembers.get(newMembers.size()-1));
    }

    @Test
    public void shipServiceTest(){
        PropertyReaderUtil.loadProperties();
        SpaceshipService service = SpaceshipServiceImpl.getInstance(null);
        List<Spaceship> ships=service.findAllSpaceships();
        Map<Role, Short> map = new HashMap<>();
        map.put(Role.PILOT, (short) 2);
        map.put(Role.COMMANDER, (short) 2);
        map.put(Role.MISSION_SPECIALIST, (short) 2);
        map.put(Role.FLIGHT_ENGINEER, (short) 2);
        Spaceship test=new SpaceshipFactory().create("Dodge challenger",123L,map);
        service.createSpaceship(test);
        List<Spaceship> newShips = service.findAllSpaceships();
        assertEquals(ships.get(ships.size()-1),newShips.get(ships.size()-1));
        ships.remove(test);
        service.deleteSpaceship(test);
        newShips=service.findAllSpaceships();
        assertEquals(ships.get(ships.size()-1),newShips.get(newShips.size()-1));
    }


}
