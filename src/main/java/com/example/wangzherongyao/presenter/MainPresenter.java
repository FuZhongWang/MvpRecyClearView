package com.example.wangzherongyao.presenter;

import com.example.wangzherongyao.model.callback.HttpUtilsCallback;
import com.example.wangzherongyao.model.http.HttpUtils;

/**
 * Created by P7XXTM1-G on 2018/4/27.
 */

public class MainPresenter extends BasePresenter {

    private final HttpUtils instantiatio;

    public MainPresenter() {
        instantiatio = HttpUtils.getInstantiatio();
    }
    public void loadData(String url){
        instantiatio.doGet(url, new HttpUtilsCallback() {
            @Override
            public void onSuccess(String string) {

            getiBaseView().onSuccess(string);

            }

            @Override
            public void onErroe(String string) {

            }
        });

    }
}
