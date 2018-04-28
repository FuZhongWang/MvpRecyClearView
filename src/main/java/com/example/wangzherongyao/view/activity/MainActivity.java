package com.example.wangzherongyao.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.wangzherongyao.R;
import com.example.wangzherongyao.model.bean.HeadlineBean;
import com.example.wangzherongyao.presenter.MainPresenter;
import com.example.wangzherongyao.util.constant.constants;
import com.example.wangzherongyao.view.adapter.MyRecyClerViewAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter>{
    private List<HeadlineBean.DataBeanX.DataBean> obj = new ArrayList<>();
    private RecyclerView recylist;
    private Handler handler = new Handler(){



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                obj = (List<HeadlineBean.DataBeanX.DataBean>) msg.obj;
                Log.e("sisheng", "handleMessage: "+ obj.size() );
                myRecyClerViewAdapter.setList(obj);
                myRecyClerViewAdapter.notifyDataSetChanged();

            }


        }
    };
    private MyRecyClerViewAdapter myRecyClerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter getBasePresenter() {
        return new MainPresenter();
    }

    @Override
    public void initView() {

        recylist = (RecyclerView) findViewById(R.id.recylist);

    }

    @Override
    public void initData() {
        basePresenter.loadData(constants.SITE+"?page=1");
        myRecyClerViewAdapter = new MyRecyClerViewAdapter(this);
        myRecyClerViewAdapter.setOnItemClickLitener(new MyRecyClerViewAdapter.OnItemClickLitener() {
            @Override
            public void OnItemLongClickLitener(View v, int position) {
                obj.remove(position);
                myRecyClerViewAdapter.notifyDataSetChanged();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylist.setLayoutManager(linearLayoutManager);
        recylist.setAdapter(myRecyClerViewAdapter);

    }


    @Override
    public void onSuccess(String string) {
        Gson gson = new Gson();
        HeadlineBean headlineBean = gson.fromJson(string, HeadlineBean.class);
        List<HeadlineBean.DataBeanX.DataBean> data = headlineBean.getData().getData();
        Message message = handler.obtainMessage();
        message.obj=data;
        message.what=1;
        handler.sendMessage(message);


    }

    @Override
    public void onErroe(String string) {

    }
}
