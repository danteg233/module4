import java.io.*;
import java.util.Properties;

public abstract class PropertyFileReader {

    private static String baseUrl = null;

    public static String getBaseUrl() {
        if (baseUrl.equals(null)){
            read();
        }
        return baseUrl;
    }

    public static void read(){
        Properties properties = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream("config.properties");
            properties.load(input);
            baseUrl = properties.getProperty("baseUrl");

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
