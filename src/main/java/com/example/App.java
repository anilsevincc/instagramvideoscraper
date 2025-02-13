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

    private static final String API_KEY = "06a0f24150msh0db77ea66b646a1p143d16jsna5f5dcf55547";
    private static final String API_HOST = "instagram-scraper-api2.p.rapidapi.com";

    public static void main(String[] args) {

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

    public static void searchbyusernameanddownload(String usernamee) {


        OkHttpClient client = new OkHttpClient();

        //String searchbyusername = "yureklisueda";

        String url = "https://instagram-scraper-api2.p.rapidapi.com/v1/posts?username_or_id_or_url=" + usernamee;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-RapidAPI-Key", "06a0f24150msh0db77ea66b646a1p143d16jsna5f5dcf55547")
                .header("X-RapidAPI-Host", "instagram-scraper-api2.p.rapidapi.com")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful() && response.body() != null) {
                String jsonData = response.body().string();
                //System.out.println(jsonData);

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

                //String videoUrl = "https://scontent-ham3-1.cdninstagram.com/o1/v/t16/f2/m86/AQNvsdyZZ9RVj2RUHd0pVIdE0zfa7tN6DlJ9X30u1XfkhqbEysh7TnbzhaVAaorIZ2ZpS82b1OM76CNehJYgT9W4UFdtA54k_aXCdjw.mp4?efg=eyJ4cHZfYXNzZXRfaWQiOjUzMTY4MjkwNjQxMTc1MCwidmVuY29kZV90YWciOiJ4cHZfcHJvZ3Jlc3NpdmUuSU5TVEFHUkFNLkNMSVBTLkMzLjcyMC5kYXNoX2Jhc2VsaW5lXzFfdjEifQ&_nc_ht=scontent-ham3-1.cdninstagram.com&_nc_cat=100&_nc_oc=Adg9oJkPJdJXoqF5lC3p4-nDN6gg_I091cMIBptXj1bU0oZqklNgAGdFsAGq_CFQhiE&vs=834d17e21ba3ad56&_nc_vs=HBksFQIYUmlnX3hwdl9yZWVsc19wZXJtYW5lbnRfc3JfcHJvZC9GQjQ2OTEzMkFENzVCQkU5MEI4MTc0NUZENkI5MURCMV92aWRlb19kYXNoaW5pdC5tcDQVAALIAQAVAhg6cGFzc3Rocm91Z2hfZXZlcnN0b3JlL0dObkNxUnNocjlBMU5qUUlBR1RIb0RabEo4RXlicV9FQUFBRhUCAsgBACgAGAAbAogHdXNlX29pbAExEnByb2dyZXNzaXZlX3JlY2lwZQExFQAAJszLqKyC5PEBFQIoAkMzLBdAMgAAAAAAABgSZGFzaF9iYXNlbGluZV8xX3YxEQB1_gcA&ccb=9-4&oh=00_AYBqj8AF0jseGqUUwQC6sQAHWngdznoUlFhhIVEY1ediSQ&oe=67AEE1C0&_nc_sid=1d576d";


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
                    //System.out.println(video_url);

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

