package com.epam.jwd.core_final.domain;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public class ApplicationProperties {
    public static String inputRootDir = "src/main/resources/input/";
    public static String outputRootDir = "src/main/resources/output/";
    public static String crewFileName = "crew";
    public static String missionsFileName = "missions";
    public static String spaceshipsFileName = "spaceships";
    public static Integer fileRefreshRate = 1;
    public static String dateTimeFormat = "dd-MM-yyyy";
    //todo
}
