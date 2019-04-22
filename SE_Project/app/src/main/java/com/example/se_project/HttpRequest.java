package com.example.se_project;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * This class is to deal with http request of json object ({@link JSONObject}).
 */
public final class HttpRequest {
    private HttpRequest(){}
    /**
     * To produce a {@link JSONObject} request by url and json.
     *
     * @param url_String the target url in string type.
     * @param json       the json object to send in {@link JSONObject} type.
     * @return the json object
     * @throws Exception the exception of {@link java.net.MalformedURLException},{@link IOException},{@link java.net.ProtocolException}
     */
    public static JSONObject jsonRequest(String url_String, JSONObject json) throws Exception{
        URL url = new URL(url_String);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");//设置参数类型是json格式
        connection.connect();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
        writer.write(json.toString());
        writer.close();

        int responseCode = connection.getResponseCode();
        InputStream inputStream = connection.getInputStream();
        String result = inputStream2String(inputStream);//将流转换为字符串。

        return JSONObject.parseObject(result);
    }

    private static String inputStream2String (InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b,n));
        }
        return out.toString();
    }

}

