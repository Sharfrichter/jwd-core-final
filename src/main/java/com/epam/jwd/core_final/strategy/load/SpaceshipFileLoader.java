package com.epam.jwd.core_final.strategy.load;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.strategy.LoadFromFileStrategy;
import com.epam.jwd.core_final.util.Logger;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class SpaceshipFileLoader implements LoadFromFileStrategy<Spaceship> {

    @Override
    public List<Spaceship> load(Path path) throws IOException {
        ArrayList<String[]> patterns = new ArrayList<>();
        ArrayList<String[]> values = new ArrayList<>();
        ArrayList<Spaceship> ships = new ArrayList<>();


            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));
            ArrayList<String> pattern = new ArrayList<>();
            ArrayList<String> value = new ArrayList<>();
            StringBuilder buffer = new StringBuilder();
            while (reader.ready()){
                char ch = (char) reader.read();
                if(ch=='#'){
                    while (ch!='\n'){
                        ch = (char) reader.read();
                        if(ch=='\r'||ch=='\n');
                        else if(ch==' '||ch=='{'){
                            if(!buffer.toString().isEmpty()){
                                pattern.add(buffer.toString());
                            }
                            buffer = new StringBuilder();
                        }else if(ch==';'||ch==':'||ch==','){
                            pattern.add(buffer.toString());
                            buffer = new StringBuilder();
                        }else if(ch=='}'){
                            pattern.add(buffer.toString());
                            String[] tmp = new String[pattern.size()];
                            tmp = pattern.toArray(tmp);
                            pattern.clear();
                            patterns.add(tmp);
                            buffer = new StringBuilder();
                        }else if(buffer.toString().contains("..")){
                            reader.read();
                            buffer = new StringBuilder();
                        }
                        else {
                            buffer.append(ch);
                        }
                    }
                }
                if(ch=='{'||ch=='\n'||ch=='\r');
                else if(ch==';'||ch==':'||ch==','){
                    value.add(buffer.toString());
                    buffer = new StringBuilder();
                }else if(ch=='}'){
                    value.add(buffer.toString());
                    String[] tmp = new String[value.size()];
                    tmp = value.toArray(tmp);
                    value.clear();
                    values.add(tmp);
                    buffer = new StringBuilder();
                }else {
                    buffer.append(ch);
                }

            }
            for(String[]s:values){
            ships.add(create(patterns.get(0), s));
        }
        Logger.info("spaceships was loaded");
        return ships;

    }

    public Spaceship create(String[] pattern,String[] values){
        SpaceshipFactory factory = new SpaceshipFactory();
        String name = values[0];
        Long distance = Long.parseLong(values[1]);
        Map<Role, Short> crew = new HashMap<>();
        for(int i=2;i<values.length;i+=2){
            Role role=Role.resolveRoleById(Integer.parseInt(values[i]));
            Short count = Short.parseShort(values[i + 1]);
            crew.put(role, count);

        }
        return factory.create(name, distance, crew);
    }

}
