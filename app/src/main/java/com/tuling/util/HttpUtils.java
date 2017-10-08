package com.tuling.util;

import android.util.Log;

import com.example.feng.bean.ChatMessage;
import com.example.feng.bean.Result;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by feng on 2017/10/3.
 */


public class HttpUtils {

    private static final String URL = "http://www.tuling123.com/openapi/api";
    private static final String API_KEY = "7bfb211ef53141e2b6660af24e66e780";
    /*
    * 发送一个消息
    * @param msg
    * @return
    *
    * */
    public static ChatMessage sendMessage(String msg) {
        String jsonRes = null;
        try {
            jsonRes = doGet(msg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ChatMessage chatMessage = new ChatMessage();

        Result result = null;
        result = gson.fromJson(jsonRes, Result.class);
        chatMessage.setMsg(result.getText());
        chatMessage.setDate(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);
        return chatMessage;

    }

    public static String doGet(String msg) throws MalformedURLException {
        String result = "";
        Log.v("TAG","helo");
        String url = setParams(msg);
        java.net.URL urlNet = new java.net.URL(url);
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            conn = (HttpURLConnection) urlNet.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setConnectTimeout(5 * 1000);
        conn.setReadTimeout(5 * 1000);
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        try {
            is = conn.getInputStream();
            int len = -1;
            byte[] buff = new byte[128];
            baos = new ByteArrayOutputStream();
            while ((len = is.read(buff)) != -1) {
                baos.write(buff, 0, len);

            }
            baos.flush();
            result = new String(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {

                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return result;
    }

    public static String setParams(String msg) {

        String url = null;

        try {
            url = URL + "?key=" + API_KEY + "&info="
                    + URLEncoder.encode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;

    }


}