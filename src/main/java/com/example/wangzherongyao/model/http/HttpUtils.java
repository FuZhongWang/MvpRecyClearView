package com.example.wangzherongyao.model.http;

import com.example.wangzherongyao.model.callback.HttpUtilsCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by P7XXTM1-G on 2018/4/27.
 */

public class HttpUtils implements Callback {
    private  OkHttpClient okHttpClient;
    private HttpUtilsCallback httpUtilsCallback;
    //volatile
    private static   HttpUtils httpUtils;
    private  Request.Builder  rebuilder;

    private HttpUtils() {
        okHttpClient = new OkHttpClient.Builder().build();
        rebuilder = new Request.Builder();
    }
    public static HttpUtils getInstantiatio(){
        if (httpUtils==null){
            synchronized (HttpUtils.class){
                 if (httpUtils==null){
                     httpUtils=new HttpUtils();
                 }
            }
        }

        if (httpUtils==null){

            httpUtils=new HttpUtils();
        }
    return httpUtils;
    }
    public void doGet(String url,HttpUtilsCallback httpUtilsCallback){

        this.httpUtilsCallback=httpUtilsCallback;
        Request request = rebuilder.get().url(url).build();
        okHttpClient.newCall(request).enqueue(this);


    }
    public void doPost(String url, HashMap<String,String> hashMap, HttpUtilsCallback httpUtilsCallback){
        this.httpUtilsCallback=httpUtilsCallback;
        FormBody.Builder builder = new FormBody.Builder();
        Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value= hashMap.get(key);
            builder.add(key,value);

        }
       FormBody formBody = builder.build();

        Request request = rebuilder.url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(this);

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String string = response.body().string();
        httpUtilsCallback.onSuccess(string);

    }
}
