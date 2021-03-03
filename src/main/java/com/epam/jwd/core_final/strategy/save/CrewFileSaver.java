package com.epam.jwd.core_final.strategy.save;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.strategy.SaveInFileStrategy;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class CrewFileSaver implements SaveInFileStrategy<CrewMember> {

    @Override
    public void save(Path path, List<CrewMember> list) throws IOException {

        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));
        String pattern = "#role,name,rank;role,name,rank;role,name,rank;role,name,rank;...";
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.toFile())));
        writer.write(pattern);
        writer.write("\r");
        writer.write("\n");
        StringBuilder builder = new StringBuilder();
        for(CrewMember member:list){
            builder.append(member.getRole().getId());
            builder.append(",");
            builder.append(member.getName());
            builder.append(",");
            builder.append(member.getRank().getId());
            builder.append(";");
            writer.write(builder.toString());
            builder = new StringBuilder();
        }
        writer.write("\r");
        writer.write("\n");
        writer.flush();
        writer.close();

    }
}
