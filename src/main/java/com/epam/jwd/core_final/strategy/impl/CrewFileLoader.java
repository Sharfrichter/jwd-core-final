package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.strategy.LoadFromFileStrategy;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CrewFileLoader implements LoadFromFileStrategy {
    @Override
    public List<? extends AbstractBaseEntity> load(Path path) {
        ArrayList<String[]> fieldsNames=new ArrayList<>();
        ArrayList<String[]> crewValues = new ArrayList<>();
        ArrayList<CrewMember> crew = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));

            ArrayList<String> pattern=new ArrayList<>();
            ArrayList<String> person = new ArrayList<>();

            StringBuilder buffer=new StringBuilder("");
            while (reader.ready()){
                char ch= (char) reader.read();
                if(ch=='#'){
                    while (ch!='\n'){
                        ch= (char) reader.read();
                        if(ch==','||ch=='\n'){
                            pattern.add(buffer.toString());
                            buffer = new StringBuilder();
                        }else if(ch==';'){
                            pattern.add(buffer.toString());
                            String[] tmp=new String[pattern.size()];
                            fieldsNames.add(pattern.toArray(tmp));
                            pattern.clear();
                            buffer = new StringBuilder();
                        }else {
                            buffer.append(ch);
                        }
                    }
                }
                if(ch=='\n');
                else if(ch==','){
                    person.add(buffer.toString());
                    buffer = new StringBuilder();
                }else if(ch==';'){
                    person.add(buffer.toString());
                    String[] tmp=new String[person.size()];
                    crewValues.add(person.toArray(tmp));
                    person.clear();
                    buffer = new StringBuilder();
                }else {
                    buffer.append(ch);
                }



            }
        }catch (IOException e){
            e.printStackTrace();
        }
        for(int i=0;i<crewValues.size();i++){
            if(i>=fieldsNames.size()){
                crew.add(create(fieldsNames.get(fieldsNames.size() - 1), crewValues.get(i)));
            }else {
                crew.add(create(fieldsNames.get(i), crewValues.get(i)));
            }
        }

        return crew;
    }

    CrewMember create(String[]fields,String[]values){
        String name=null;
        Role role=null;
        Rank rank=null;
        CrewMemberFactory factory = new CrewMemberFactory();
        for(int i=0;i<fields.length;i++){
            Field field=null;
            try {
                field=CrewMember.class.getDeclaredField(fields[i]);}
            catch (NoSuchFieldException e){
                try{
                    field = CrewMember.class.getSuperclass().getDeclaredField(fields[i]);
                }catch (NoSuchFieldException ex){
                    System.out.println(ex.getMessage());
                }

            }
                field.setAccessible(true);
                if(field.getType().equals(Rank.class)){
                    rank = Rank.resolveRankById(Integer.parseInt(values[i]));
                }else if(field.getType().equals(Role.class)){
                    role = Role.resolveRoleById(Integer.parseInt(values[i]));
                }else if(field.getType().equals(String.class)){
                    name = values[i];

                }
                    field.setAccessible(false);

        }
        return factory.create(name,role,rank);
    }



}
