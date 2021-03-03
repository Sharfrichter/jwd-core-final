package com.epam.jwd.core_final.strategy.save;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.strategy.SaveInFileStrategy;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.List;

public class SpaceshipFileSaver implements SaveInFileStrategy<Spaceship> {
    @Override
    public void save(Path path, List<Spaceship> list) throws IOException {
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.toFile())));
        StringBuilder builder = new StringBuilder();
        String patterns="#name;distance;crew {roleid:count,roleid:count,roleid:count,roleid:count}\r\n" +
                "#name;distance;crew {roleid:count,roleid:count,roleid:count,roleid:count}\r\n" +
                "#...\r\n";
        writer.write(patterns);
        for(Spaceship ship:list){
            builder.append(ship.getName()).append(";");
            builder.append(ship.getFlightDistance()).append(";");
            builder.append("{1:").append(ship.getCrew().get(Role.resolveRoleById(1))).append(",");
            builder.append("2:").append(ship.getCrew().get(Role.resolveRoleById(2))).append(",");
            builder.append("3:").append(ship.getCrew().get(Role.resolveRoleById(3))).append(",");
            builder.append("4:").append(ship.getCrew().get(Role.resolveRoleById(4))).append("}");
            writer.write(builder.toString());
            writer.write("\r");
            writer.write("\n");
            builder = new StringBuilder();
        }

        writer.flush();
        writer.close();
    }
}
