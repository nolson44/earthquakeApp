package nolson.earthquakeapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;

public class EarthquakeApp extends ListActivity {

	private static String url = "http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman";
	private JSONArray eq = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        // JSON Data *************************
        JSONParser jparser = new JSONParser();
        JSONObject json = jparser.getJSONFromUrl(url);
        
        ArrayList<String> list = new ArrayList();

        try {
        	//Get earthquakes list
        	eq = json.getJSONArray("earthquakes");
        	
        	for(int i = 0; i < eq.length(); ++i){
        		//Get data from JSONObject
        		JSONObject quake = eq.getJSONObject(i);
        		String eqid = quake.getString("eqid");
            	double mag = quake.getDouble("magnitude");
            	String magnitude = Double.toString(mag);
            	String datetime = quake.getString("datetime");
            	
            	//Store data in the ArrayList list
            	list.add(eqid);
            	list.add(datetime);
            	list.add(magnitude);
        	}
        } catch (JSONException e) {
        	e.printStackTrace();
        }
        
        //Create the array used for the adapter and populate it with the appropriate information
          //Earthquakes[x][0] = eqid            j = 0
          //Earthquakes[x][1] = datetime        j = 1
          //Earthquakes[x][2] = magnitude       j = 2
        String[][] Earthquakes = new String[list.size()/3][3];
        for(int i = 0; i < list.size()/3; ++i) {
        	for(int j = 0; j < 3; ++j) {
        		Earthquakes[i][j] = list.get(i*3 + j);
        	}
        }
      
        setListAdapter(new ListAdapter(this, Earthquakes));
    }
}
