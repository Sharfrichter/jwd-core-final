import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {
    @Test
    public void CrewFactoryTest(){
        EntityFactory factory = new CrewMemberFactory();
        CrewMember target = new CrewMember();
        target.setName("Alex");
        target.setRole(Role.COMMANDER);
        target.setRank(Rank.FIRST_OFFICER);
        assertEquals(factory.create("Alex",Rank.FIRST_OFFICER,Role.COMMANDER),target);
    }

    @Test
    public void PlanetFactoryTest(){
        EntityFactory factory = new PlanetFactory();
        Planet target  = new Planet();
        target.setName("Earth");
        target.setX(12L);
        target.setY(13L);
        assertEquals(factory.create("Earth",12L,13L),target);
        target=new Planet();
        assertEquals(target,factory.create());
    }
    @Test
    public void SpaceshipFactoryTest(){
        EntityFactory factory = new SpaceshipFactory();
        Spaceship target = new Spaceship();
        target.setName("Dodge");
        target.setFlightDistance(1234556L);
        assertEquals(target,factory.create("Dodge",1234556L));

    }
}
