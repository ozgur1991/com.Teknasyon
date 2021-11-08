package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
//bu objeyi projenin heryerinden class ismiyle cagrilabilmesi icin static yapiyoruz
    //class disindan mudahale olmamasi icin private yapiyoruz

    static private Properties properties;

    static{
        String path="configuration.properties";
        try {
            FileInputStream fileInputStream=new FileInputStream(path);
            properties=new Properties();
            properties.load(fileInputStream);

            fileInputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String getProperty(String key) {

        return properties.getProperty(key);
    }



}
