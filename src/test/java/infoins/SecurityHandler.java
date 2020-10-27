package infoins;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SecurityHandler {

    private static final String GRANT_TYPE = "password";

    public String obtainAccessToken(String clientId, String secret) {
        String accessToken = getAccessToken("qauser", "qauser", clientId, GRANT_TYPE, secret);
        return accessToken;
    }

    public String getAccessToken(String username, String password, String clientId, String grantType, String secret) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("username", username);
        requestBody.add("password", password);
        requestBody.add("client_id", clientId);
        requestBody.add("grant_type", grantType);
        requestBody.add("client_secret", secret);

        HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);

        RestTemplate restTemplate =new RestTemplate();
        String url = "http://52.230.11.5:8080/auth/realms/INFOINS/protocol/openid-connect/token";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class);

        String body = response.getBody();
        JSONObject jsonObject = new JSONObject(body);
        String access_token = (String) jsonObject.get("access_token");
        return "Bearer " + access_token;

    }

}
