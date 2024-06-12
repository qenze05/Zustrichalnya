package com.smakab.datingapp.zustrichalnya.FireBase;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FirebaseService {

    private static final String DATABASE_URL = "https://YOUR_PROJECT_ID.firebaseio.com/";
    private static final String API_KEY = "YOUR_API_KEY";

    public static void sendData(String path, JsonObject data) throws Exception {
        URL url = new URL(DATABASE_URL + path + ".json?auth=" + API_KEY);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setDoOutput(true);

        try (OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = data.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = httpURLConnection.getResponseCode();
        if (code != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + code);
        }
    }

    public static JsonObject getData(String path) throws Exception {
        URL url = new URL(DATABASE_URL + path + ".json?auth=" + API_KEY);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        int code = httpURLConnection.getResponseCode();
        if (code != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + code);
        }

        try (InputStream is = httpURLConnection.getInputStream()) {
            InputStreamReader reader = new InputStreamReader(is, "utf-8");
            return JsonParser.parseReader(reader).getAsJsonObject();
        }
    }

    public static void deleteData(String path) throws Exception {
        URL url = new URL(DATABASE_URL + path + ".json?auth=" + API_KEY);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("DELETE");

        int code = httpURLConnection.getResponseCode();
        if (code != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + code);
        }
    }

    public static void main(String[] args) {
        try {
            JsonObject data = new JsonObject();
            data.addProperty("name", "John Doe");
            sendData("users/user1", data);

            JsonObject responseData = getData("users/user1");
            System.out.println(responseData);

            deleteData("users/user1");
            System.out.println("Data deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
