package infoins;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import io.restassured.RestAssured;

/**
 * @author : Eranda Kodagoda
 *  * @date : August 10, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class BaseClass {
    public String url;
    public String bearerAdminToken = null;
    Properties properties;


    public String getURL() throws IOException {
        properties = new Properties();
        String userdir = System.getProperty("user.dir");
        String propertydir = "\\src\\test\\java\\infoins\\global.properties";
        FileInputStream fileInputStream = new FileInputStream(userdir + propertydir);
        properties.load(fileInputStream);
        RestAssured.proxy("192.168.15.5",8080);
        url = properties.getProperty("url");
        return url;
    }

    public String getBearerToken() throws IOException {
        getURL();
        String bearerToken = properties.getProperty("bearerToken");
        return bearerToken;
    }

    public static String getGeneratedString(String file) throws IOException {
        String filePath = System.getProperty("user.dir") + "\\payloads\\" + file;
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

}
