import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class OAuthClientCredentials {

    public static void main(String[] args) throws Exception {
        String tokenEndpoint = "https://auth-server.com/oauth/token"; // Change this to your OAuth token endpoint
        String clientId = "YOUR_CLIENT_ID";
        String clientSecret = "YOUR_CLIENT_SECRET";

        String accessToken = getAccessToken(tokenEndpoint, clientId, clientSecret);
        System.out.println("Access Token: " + accessToken);
    }

    private static String getAccessToken(String tokenEndpoint, String clientId, String clientSecret) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(tokenEndpoint);
        
        // Add headers if necessary (e.g., for Basic Auth or other authentication mechanisms)
        
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "client_credentials"));
        params.add(new BasicNameValuePair("client_id", clientId));
        params.add(new BasicNameValuePair("client_secret", clientSecret));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse response = httpClient.execute(httpPost);
        
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            // Normally, you'd parse the responseBody as JSON to extract the access token.
            // For simplicity, we're just returning the entire response body.
            return responseBody;
        } else {
            throw new RuntimeException("Failed to obtain access token. Status code: " + statusCode);
        }
    }
}
