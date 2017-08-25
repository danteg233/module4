package core;

import java.io.*;
import java.util.Properties;

public abstract class PropertyFileReader {

    private static String string = null;

    public static String getValue() {
        return string;
    }

    public static void read(String prop){
        Properties properties = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream("src/main/resources/config.properties");
            properties.load(input);
            string = properties.getProperty(prop);

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
