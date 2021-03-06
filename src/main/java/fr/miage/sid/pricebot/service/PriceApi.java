package fr.miage.sid.pricebot.service;

import com.fasterxml.jackson.core.io.UTF8Writer;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Component
public class PriceApi {
    String token = "ISSRRDXJQFOQLDBPUJZPCTGICEECASJIJACLCYUPLCGHPLVWXQCYTUJQMXHFHXQF";

    @Autowired
    public PriceApi(){

    }

    public JSONObject getPrice(String valeur) throws Exception {
        //String result = getHTML("https://api.priceapi.com/products/bulk/5a579df1ad19f50d33f9a42c?token=ISSRRDXJQFOQLDBPUJZPCTGICEECASJIJACLCYUPLCGHPLVWXQCYTUJQMXHFHXQF");
       // String result = postHTML("https://api.priceapi.com/jobs" );
        PriceApi connApi = new PriceApi();
        JSONObject result = connApi.request(valeur,"google-shopping","fr","keyword");
        String jobId = "";

        jobId = (String) result.get("job_id");

        Boolean end = false;
        String status;
        while (!end){
            Thread.sleep(500);
            JSONObject etat = connApi.getStatus(jobId);

            Boolean isComplete = false;

            status = (String) etat.get("status");
            isComplete = status.equals("finished");

            if (isComplete) {
                result = connApi.getResults(jobId, "json");
                end = true;
            }
        }
        return result;
    }
    private JSONObject getResults(String jobId, String format) throws IOException, URISyntaxException, JSONException {

        String response = getRequest("/products/bulk/" + jobId + '.' + format,"token=" + token);
        return  new JSONObject(response);
    }

    private JSONObject getStatus(String jobId) throws JSONException, IOException, URISyntaxException {

        String response = getRequest("/jobs/" + jobId, "token=" + token);
        return new JSONObject(response);
    }
    private String getRequest(String path, String query) throws URISyntaxException, IOException {
        String response = "";


        URI uri = new URI("https", "api.priceapi.com", path, query, null);
        URL url = uri.toURL();
        URLConnection conn = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));

        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            response += inputLine;
        }
        br.close();

        return response;
    }

    private JSONObject request(String values, String source, String country,
                              String key) throws JSONException, IOException, URISyntaxException {
        String st = "token=%s&source=%s&country=%s&key=%s&values=%s";
        String query = String.format(st, token, source, country, key, values);

        String response = postRequest("/jobs", query);

        JSONObject json = null;
        json = new JSONObject(response);

        return json;
    }
    private String postRequest(String path, String query) throws URISyntaxException, IOException {
        String response = "";

        URI uri = new URI("https", "api.priceapi.com", path, null);
        URL url = uri.toURL();
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);

        OutputStreamWriter writer = new OutputStreamWriter(
                conn.getOutputStream(), StandardCharsets.UTF_8);
        writer.write(query);
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),StandardCharsets.UTF_8));

        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            response += inputLine;
        }
        reader.close();

        return response;
    }

}
