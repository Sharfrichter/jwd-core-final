import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import org.junit.jupiter.api.Test;
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
}
