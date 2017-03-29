package edu.nju.whattoeat.Util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by st0001 on 2017/3/29.
 */

public class HttpUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static void sendHttpRequestGet(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
//                HttpURLConnection connection = null;
//                try{
//                    URL url = new URL(address);
//                    connection = (HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    connection.setDoOutput(true);
//                    connection.setDoInput(true);
//                    StringBuilder response = new StringBuilder();
//                    if(listener != null){
//                        //回调onFinish方法
//                        listener.onFinish(response.toString());
//                    }
//                }catch (Exception e){
//                    //回调error方法
//                    if(listener != null){
//                        listener.onError(e);
//                    }
//                }finally{
//                    if(connection != null){
//                        connection.disconnect();
//                    }
//                }
            }
        }).start();

    }

    public static void sendHttpRequestPost(final String address, final JSONObject object,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {

                postJson(address,object,listener);
//                HttpURLConnection connection = null;
//                try{
//                    URL url = new URL(address);
//                    connection = (HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("POST");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    connection.setDoOutput(true);
//                    connection.setDoInput(true);
//                    connection.setRequestProperty("Content-Type","application/json");
//                    connection.setUseCaches(false);
//                    //把数据以流的方式写给服务器
//                    connection.getOutputStream().write(String.valueOf(object).getBytes());
//
//                    String response = new String();
//                    response = connection.getResponseMessage();
//                    if(listener != null){
//                        //回调onFinish方法
//                        listener.onFinish(response);
//                    }
//                }catch (Exception e){
//                    //回调error方法
//                    if(listener != null){
//                        listener.onError(e);
//                    }
//                }finally{
//                    if(connection != null){
//                        connection.disconnect();
//                    }
//                }
             }
        }).start();

    }


    private static void postJson(String address,JSONObject object,final HttpCallbackListener listener){
        //申明给服务端传递一个json串
        //创建一个OKHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody（参数1：数据类型，参数2：传递的json串）

        RequestBody requestBody = RequestBody.create(JSON,String.valueOf(object).getBytes());
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        //发送请求获取响应
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(listener != null){
                listener.onFinish(response);
            }

        } catch (IOException e) {
           if(listener != null){
               listener.onError(e);
           }
        }
    }

    public static String  pareJSONWithObject(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String status = jsonObject.getString("status");
                return status;
            }
        }catch (Exception e){
            return e.getMessage();
        }finally {
            return " ";
        }
    }
}
