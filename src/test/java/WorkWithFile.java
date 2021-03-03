import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.strategy.load.CrewFileLoader;
import com.epam.jwd.core_final.strategy.load.SpaceshipFileLoader;
import com.epam.jwd.core_final.strategy.save.CrewFileSaver;
import com.epam.jwd.core_final.strategy.save.SpaceshipFileSaver;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import static org.junit.jupiter.api.Assertions.*;

public class WorkWithFile {
    private static final String dir = "src/test/resources/input/";

    @Test
    public void testSaveCrew(){
        CrewFileSaver saver = new CrewFileSaver();
        CrewFileLoader loader = new CrewFileLoader();
        List<CrewMember> members = new ArrayList<>();
        List<CrewMember> loadedMembers = null;

        CrewMemberFactory factory = new CrewMemberFactory();
        members.add(factory.create("Alex",Rank.CAPTAIN, Role.FLIGHT_ENGINEER));
        members.add(factory.create("Kira",Rank.TRAINEE, Role.PILOT));
        members.add(factory.create("John",Rank.FIRST_OFFICER, Role.COMMANDER));
        try {
            saver.save(Path.of(dir+"crew"),members);
        } catch (IOException e) {
            System.out.println("IO exception");
        }
        try {
            loadedMembers= loader.load(Path.of(dir + "crew"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(loadedMembers,members);

    }
    @Test
    public void testSaveShip() throws IOException {
        SpaceshipFileSaver saver = new SpaceshipFileSaver();
        SpaceshipFileLoader loader = new SpaceshipFileLoader();

        List<Spaceship> spaceships=new ArrayList<>();
        SpaceshipFactory factory=new SpaceshipFactory();
        Map<Role, Short> map = new HashMap<>();
        map.put(Role.COMMANDER, (short) 1);
        map.put(Role.FLIGHT_ENGINEER, (short) 2);
        map.put(Role.MISSION_SPECIALIST, (short) 1);
        map.put(Role.PILOT, (short) 2);
        spaceships.add(factory.create("Dodge viper", 123456L, map));
        saver.save(Path.of(dir+"spaceships"),spaceships);
        List<Spaceship> loaded=loader.load(Path.of(dir + "spaceships"));
        assertEquals(spaceships,loaded);
    }
}
