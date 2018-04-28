package com.example.wangzherongyao.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wangzherongyao.presenter.BasePresenter;
import com.example.wangzherongyao.view.interfaces.IBaseView;

/**
 * Created by P7XXTM1-G on 2018/4/27.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {

    public T basePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        basePresenter=getBasePresenter();
        basePresenter.attachView(this);
        initView();
        initData();

    }
    public abstract int setContentView();
    public abstract  T getBasePresenter();
    public abstract void initView();
    public abstract void initData();
}
