package kuby.web.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import kuby.web.testBase.TestBase;

public class APIhandlerutils extends TestBase {


	public static final Logger logg=Logger.getLogger(APIhandlerutils.class.getName());
	
	 public static void deleteInventoryMethod() {
		 logg.info("Delete Inventory Method");
   	  try {
	            URL url = new URL(prop.getProperty("DecommisionEndPointUrl"));
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	            // Set the request method to DELETE
	            connection.setRequestMethod("GET");
	            // Get the response code
	            int responseCode = connection.getResponseCode();

	            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
	                logg.info("DELETE request was successful. Data deleted.");
	            } else {
	                logg.info("DELETE request failed with response code: " + responseCode);
	                // Optionally, you can read and print the response body for more information
	                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    System.out.println(line);
	                    logg.info("Line = "+line);
	                }
	                reader.close();
	            }

	            connection.disconnect();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}

