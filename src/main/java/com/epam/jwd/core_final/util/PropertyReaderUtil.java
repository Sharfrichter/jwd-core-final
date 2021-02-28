package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static void loadProperties() {
        final String propertiesFileName = "resources/application.properties";
        try {
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Path.of("src/main/"+propertiesFileName).toFile())));
            Field[] fields = ApplicationProperties.class.getDeclaredFields();
            while (reader.ready()){
                lines.add(reader.readLine());
            }
            for(int i=0;i<lines.size();i++){
                for(int j=0;j<fields.length;j++){
                    if(lines.get(i).contains(fields[j].getName())){
                        fields[j].setAccessible(true);
                        if(fields[j].getType().equals(String.class)){
                            fields[j].set(fields[j],lines.get(i).substring(lines.get(i).indexOf("=")+1));
                        }else if(fields[j].getType().equals(Integer.class)){
                            fields[j].set(fields[j],Integer.parseInt(lines.get(i).substring(lines.get(i).indexOf("=")+1)));
                        }

                        fields[j].setAccessible(false);

                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
