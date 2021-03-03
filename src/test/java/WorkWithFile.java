import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.strategy.load.CrewFileLoader;
import com.epam.jwd.core_final.strategy.save.CrewFileSaver;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WorkWithFile {
    private static final String dir = "src/test/resources/input/";

    @Test
    public void testSave(){
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
}
