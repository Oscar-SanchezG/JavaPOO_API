package com.esanchez.getRepository;
import com.esanchez.model.Image;
import com.esanchez.util.ConectionAPI;
import java.io.BufferedReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class GetImageRepositoryImpl implements GetImageRepository<Image>{
    private HttpURLConnection getConnection() throws IOException {
        return ConectionAPI.getInstance("2");
    }

    @Override
    public List<Image> readJson() {
        // Leer la respuesta JSON
        HttpURLConnection connection = null;
        try {
            connection = getConnection();
            connection.setRequestMethod("GET");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Leer la respuesta JSON
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder response = new StringBuilder();
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            response.append(line);
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String json = response.toString();


        return searchField(json);
    }

    private static Image encapsulate(Long id, Long albumId, String title, String url, String thumbnailUrl) {
        Image i = new Image();
        i.setId(id);
        i.setAlbumId(albumId);
        i.setTitle(title);
        i.setUrl(url);
        i.setThumbnailUrl(thumbnailUrl);

        return i;
    }

    private static List<Image> searchField(String s) {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        List<Image> images = new ArrayList<>();
        try {
            jsonArray  = (JSONArray) parser.parse(s);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Long id = (Long) jsonObject.get("id");
                Long albumId = (Long) jsonObject.get("albumId");
                String title = (String) jsonObject.get("title");
                String url = (String) jsonObject.get("url");
                String thumbnailUrl = (String) jsonObject.get( "thumbnailUrl");
                Image aux = encapsulate(id, albumId, title, url, thumbnailUrl);
                images.add(aux);
                System.out.println("Recuperando img = " + title);
            }

        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
        return images;
    }

    private static String searchField(String s, String fieldF, String fieldC) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        String sC;
        String sF;
        try {
            jsonObject = (JSONObject) parser.parse(s);
            jsonObject = (JSONObject)  jsonObject.get(fieldF);
            sF = (String)  jsonObject.get(fieldC);


        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
        return sF;
    }

    private static Long searchFieldLong(String s, String field) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(s);
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
        return (Long) jsonObject.get(field);
    }
}
