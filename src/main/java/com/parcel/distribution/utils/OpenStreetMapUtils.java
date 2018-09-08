package com.parcel.distribution.utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenStreetMapUtils{


    private static OpenStreetMapUtils instance = null;


    public OpenStreetMapUtils() {

    }

    public static OpenStreetMapUtils getInstance() {
        if (instance == null) {
            instance = new OpenStreetMapUtils();
        }
        return instance;
    }

    private String getRequest(String url) throws Exception {

        final URL obj = new URL(url);
        final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) {
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public Coordinates getCoordinates(String address) throws JSONException {
        address = address.replace(' ', '+');
        StringBuffer query = new StringBuffer();
        query.append("https://nominatim.openstreetmap.org/search?q=");
        query.append(address);
        query.append("&format=json&addressdetails=1");

        String queryResult = null;
        try {
            queryResult = getRequest(query.toString());
        } catch (Exception e) {

        }

        if (queryResult == null) {
            return null;
        }


        JSONArray jsonArray = new JSONArray(queryResult);
        Double lat = Double.parseDouble(jsonArray.getJSONObject(0).get("lat").toString());
        Double lng = Double.parseDouble(jsonArray.getJSONObject(0).get("lon").toString());

        return new Coordinates(lat, lng);
    }
}
