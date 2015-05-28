package de.codecentric.bddsoap.jbehave.steps;

import java.util.HashMap;
import java.util.Map;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public class UrlShortenerSteps extends ScenarioSteps {

    RestTemplate restTemplate;

    public UrlShortenerSteps() {
        restTemplate = new RestTemplate();
    }

    @Step("Shorten Url for longUrl={0}")
    public String shorten(String providedUrl) throws JSONException {
        Map<String, String> urlForm = new HashMap<String, String>();
        urlForm.put("longUrl", providedUrl);
        String jsonBody = "{'longUrl':'" + providedUrl + "'}";
        
        JSONObject request = new JSONObject();
        request.put("longUrl", providedUrl);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(request.toString(),headers);
        
        
        return restTemplate.postForObject("https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyBKpVvc4WA5xpSIIQpFTl2en8X9s4SyXHw", urlForm, String.class);
    }
    
    @Step("shortUrl={0}")
    public String expand(String providedUrl) {
        return restTemplate.getForObject("https://www.googleapis.com/urlshortener/v1/url?shortUrl={shortUrl}?key=AIzaSyBKpVvc4WA5xpSIIQpFTl2en8X9s4SyXHw", String.class, providedUrl);
    }

    @Step
    public void response_should_contain_shortened_url(String returnedMessage, String expectedUrl) throws JSONException {
        String expectedJSONMessage = "{'id':'" + expectedUrl + "'}";
        JSONAssert.assertEquals(expectedJSONMessage, returnedMessage, JSONCompareMode.LENIENT);
    }
    
    @Step
    public void response_should_contain_long_url(String returnedMessage, String expectedUrl) throws JSONException {
        String expectedJSONMessage = "{'longUrl':'" + expectedUrl + "'}";
        JSONAssert.assertEquals(expectedJSONMessage, returnedMessage, JSONCompareMode.LENIENT);
    }

}