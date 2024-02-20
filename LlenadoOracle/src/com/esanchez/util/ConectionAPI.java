package com.esanchez.util;


import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;

import java.net.URL;



public class ConectionAPI {
    //private static String API_ACCESS_KEY = "xz5MUitr6CDevL5gRiAEXSHVeD14I4XYpSLvv6zTd2s";
    private static String API_URL = "https://jsonplaceholder.typicode.com/albums/";
    private static String API_URL_CONCAT = "/photos";

    private static HttpURLConnection connection;

    // Hacer la solicitud a la API de Unsplash

    public static HttpURLConnection getInstance(String param) throws IOException {
        if (connection == null){
            URL url = new URL(API_URL + param + API_URL_CONCAT);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            return connection;
        }
        return connection;
    }






}
