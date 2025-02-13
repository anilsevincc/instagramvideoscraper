package com.example;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {


    String apiKey = Config.getApiKey();
    private static final String API_HOST = "instagram-scraper-api2.p.rapidapi.com";

    public static void main(String[] args) {


        //String apiKey = Config.getApiKey();
        new Frame();

    }

    public static void downloadVideo(String videoUrl, String savePath) throws IOException {
        URL url = new URL(videoUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        InputStream inputStream = connection.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(savePath);

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();
        connection.disconnect();
    }

    public static void searchbyusernameanddownload(String usernamee, String apiKey) {


        OkHttpClient client = new OkHttpClient();


        String url = "https://instagram-scraper-api2.p.rapidapi.com/v1/posts?username_or_id_or_url=" + usernamee;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "instagram-scraper-api2.p.rapidapi.com")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful() && response.body() != null) {
                String jsonData = response.body().string();

                JSONObject jsonObject = new JSONObject(jsonData);
                JSONObject data = jsonObject.getJSONObject("data");
                JSONArray items = data.getJSONArray("items");
                int count = data.getInt("count");
                JSONObject user = data.getJSONObject("user");
                String fullName = user.getString("full_name");
                String id = user.getString("id");
                boolean isPrivate = user.getBoolean("is_private");
                String username = user.getString("username");

                System.out.println(fullName);
                System.out.println(id);
                System.out.println("IsPrivate: " + isPrivate);
                System.out.println(username);



                for (int i = 0; i < count; i++) {
                    JSONObject postNumber = items.getJSONObject(i);
                    String postId = postNumber.getString("id");
                    int likeCount = postNumber.getInt("like_count");

                    if (postNumber.has("video_url")) {

                        String video_url = postNumber.getString("video_url");

                        String savePath = "video " + i + " .mp4";

                        try {
                            downloadVideo(video_url, savePath);
                            System.out.println("Video başarıyla kaydedildi : " + savePath);
                        } catch (IOException e) {
                            System.out.println("Hata oluştu : " + e.getMessage());
                        }

                    }
                    System.out.println(postId);
                    System.out.println(likeCount);

                }

                JOptionPane.showMessageDialog(null, "Başarıyla Tamamlandı!");


            } else {
                System.out.println("Hata: " + response.code());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

