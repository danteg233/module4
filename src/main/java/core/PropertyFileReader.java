package core;

import java.io.*;
import java.util.Properties;

public abstract class PropertyFileReader {

    private static String baseUrl = null;

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void read(String prop){
        Properties properties = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream("config.properties");
            properties.load(input);
            baseUrl = properties.getProperty(prop);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(input!=null){
                try{
                    input.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
