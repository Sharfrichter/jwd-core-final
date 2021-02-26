package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.strategy.LoadFromFileStrategy;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class SpaceshipFileLoader implements LoadFromFileStrategy {

    @Override
    public List<? extends AbstractBaseEntity> load(Path path) {
        ArrayList<String[]> patterns = new ArrayList<>();
        ArrayList<String[]> ships = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));
            ArrayList<String> pattern = new ArrayList<>();
            ArrayList<String> ship = new ArrayList<>();
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
                    ship.add(buffer.toString());
                    buffer = new StringBuilder();
                }else if(ch=='}'){
                    ship.add(buffer.toString());
                    String[] tmp = new String[ship.size()];
                    tmp = ship.toArray(tmp);
                    ship.clear();
                    ships.add(tmp);
                    buffer = new StringBuilder();
                }else {
                    buffer.append(ch);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i=0;i<ships.size();i++){
            System.out.println(Arrays.toString(ships.get(i)));
        }
        return null;

    }

}
