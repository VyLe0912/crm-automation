package page.utils;

import java.time.Duration;
import java.util.Properties;

public class ConfigReader {
    Properties properties;
    public ConfigReader(){
        properties = new Properties();
        try{
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (Exception e){
            System.out.println(e);
        }
        //Get email and pass from properties

    }
    public String getUrl(){
        return properties.getProperty("url");
    }
    public Duration getTimeOut(){
        return Duration.ofSeconds(Long.parseLong(properties.getProperty("timeout")));
    }

    public String email() {
        return properties.getProperty("email");
    }

    public String password() {
        return properties.getProperty("password");
    }

}
