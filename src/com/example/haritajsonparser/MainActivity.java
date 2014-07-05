package com.example.haritajsonparser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
	
	private static String url = "http://kodbankasi.org/hknapi/harita/vericek/gezi/";
	 
	  static final String TAG_CONTACTS = "contacts";
	  public static final String TAG_ID = "id";
	  public static final String TAG_NAME = "title";
	  public static final String TAG_ENLEM = "latitude";
	  public static final String TAG_BOYLAM = "latilong";
	  public static final String TAG_SNIP = "snippet";
	  public static final String TAG_Image = "image";
	 
		JSONParser jsonParser = new JSONParser();
		JSONArray contacts = null;
		
		
		GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	     StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		map = ((MapFragment) getFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);

		try {
			// Getting Array of Contacts
			contacts = json.getJSONArray(TAG_CONTACTS);
			
			// looping through All Contacts
			for(int i = 0; i < contacts.length(); i++){
				JSONObject c = contacts.getJSONObject(i);
				
				// Storing each json item in variable
				String id = c.getString(TAG_ID);
				String name = c.getString(TAG_NAME);
				String enlem= c.getString(TAG_ENLEM);	
				String boylam= c.getString(TAG_BOYLAM);
				String snippet = c.getString(TAG_SNIP);
				System.out.println("buraya geldi");
				 map.addMarker(new MarkerOptions()
			        .title(name)//.snippet(snippet)
			        .position(new LatLng(Double.parseDouble(enlem), Double.parseDouble(boylam))).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
			       
				Log.e("hata",name);
				Log.e("hata",id);
				Log.e("hata",enlem);
				Log.e("hata",boylam);
		
				
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.854, 26.630),7));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	 
}
