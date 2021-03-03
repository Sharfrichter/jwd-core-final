package com.epam.jwd.core_final.strategy.load;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.strategy.LoadFromFileStrategy;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PlanetFileLoader implements LoadFromFileStrategy<Planet> {

    @Override
    public List<Planet> load(Path path) throws IOException {

        ArrayList<String> planetsNames = new ArrayList<>();
        ArrayList<Planet> planetsList = new ArrayList<>();
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));
            StringBuilder builder = new StringBuilder();
            Integer x=0;
            Integer y=0;
            while (reader.ready()){
                char ch = (char) reader.read();
                if(ch==','){
                    if(!builder.toString().contains("null")){
                        planetsNames.add(builder.toString());
                        planetsNames.add(x.toString());
                        planetsNames.add(y.toString());
                    }
                    builder = new StringBuilder();
                }else if(ch=='\n'||ch=='\r'){
                    char newLine=(char)reader.read();
                    if(newLine=='\n'){
                        y++;
                    }
                    /*if(!builder.toString().contains("null")&&!builder.toString().isEmpty()){
                        planetsNames.add(builder.toString());
                        planetsNames.add(x.toString());
                        planetsNames.add(y.toString());
                    }*/
                    builder = new StringBuilder();
                }else {
                    builder.append(ch);
                    x++;
                }
            }
        }
        PlanetFactory factory = new PlanetFactory();
        for(int i=0;i<planetsNames.size();i+=3){
            String name = planetsNames.get(i);
            Long x = Long.parseLong(planetsNames.get(i + 1));
            Long y = Long.parseLong(planetsNames.get(i + 2));
            planetsList.add(factory.create(name, x, y));
        }
        return planetsList;
    }
}
