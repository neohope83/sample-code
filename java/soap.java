import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SOAPRequestExample {

    public static void main(String[] args) {
        try {
            // Endpoint URL of the SOAP service
            URL url = new URL("https://example.com/soap-endpoint");

            // Open a connection to the endpoint
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // Set required headers and method
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpURLConnection.setRequestProperty("SOAPAction", "SomeSOAPActionIfRequired");
            httpURLConnection.setDoOutput(true);

            // Sample SOAP request payload
            String soapXml =
                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                    "xmlns:web=\"http://www.example.com/\">" +
                    "   <soapenv:Header/>" +
                    "   <soapenv:Body>" +
                    "      <web:YourOperation>" +  // Replace 'YourOperation' with the actual operation name
                    "         <!--Optional:-->" +
                    "         <web:YourParameter>YourValue</web:YourParameter>" + // Sample parameter
                    "      </web:YourOperation>" +
                    "   </soapenv:Body>" +
                    "</soapenv:Envelope>";

            // Write the SOAP message payload to the request body
            OutputStream reqOutputStream = httpURLConnection.getOutputStream();
            reqOutputStream.write(soapXml.getBytes());
            reqOutputStream.close();

            // Get the response code
            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Close the connection
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
