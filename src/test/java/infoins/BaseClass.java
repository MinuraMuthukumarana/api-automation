package infoins;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


/**
 * @author : Eranda Kodagoda
 *  * @date : August 10, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class BaseClass {
    public String url;
    public String ClientId;
    public String SecretKey;
    public String bearerAdminToken = null;
    Properties properties;


    public String getURL() throws IOException {
        properties = new Properties();
        String userdir = System.getProperty("user.dir");
        String propertydir = "\\src\\test\\java\\infoins\\global.properties";
        FileInputStream fileInputStream = new FileInputStream(userdir + propertydir);
        properties.load(fileInputStream);
        //RestAssured.proxy("192.168.15.5",8080);
        url = properties.getProperty("url");
        return url;
    }

    public void getBearerToken(String clientId, String secret) throws IOException {
        if (AccessTokenHolder.access_token == null) {
            SecurityHandler securityHandler = new SecurityHandler();
            AccessTokenHolder.access_token = securityHandler.obtainAccessToken(clientId,secret);
        }
    }
    //Admin Service
//    @BeforeClass
//    public void setUpAdmin() throws IOException {
//        getBearerToken("admin-service","a7eb9158-9fa3-4e00-8958-6e4660154027");
//    }

    //Umbrella Service
    @BeforeClass
    public void setUpUmbrella() throws IOException {
        getBearerToken("umbrella-service","f9d8762e-6629-46d5-920b-676784c54067");
    }

    //ProductBuilder Service
//    @BeforeClass
//    public void setUpProductBuilder() throws IOException {
//        getBearerToken("product-builder","5d55b498-67f4-4a2b-87dc-3adb82ca93a4");
//    }


    public static String getGeneratedString(String file) throws IOException {
        String filePath = System.getProperty("user.dir") + "\\payloads\\" + file;
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

}
