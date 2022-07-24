package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

    public class ConfigReader {
    private static FileInputStream input;
    private static Properties properties;
    static{
        String path = "/Users/hally/IdeaProjects/MindtekTestNG/src/main/resources/configurations/Configuration.properties";
        try{
     properties = new Properties();
     input = new FileInputStream(path);
     properties.load(input);
        }catch (FileNotFoundException e ){
            System.out.println("Path for properties file is invalid");
        }catch (IOException e){
            System.out.println("Failed to load the properties file");
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                System.out.println("Exception occurred while closing input object");
            }
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
