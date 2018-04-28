package com.example.wangzherongyao.presenter;

import com.example.wangzherongyao.view.interfaces.IBaseView;

/**
 * Created by P7XXTM1-G on 2018/4/27.
 */

 public class BasePresenter<T extends IBaseView> {
    private T iBaseView ;
     public void attachView(T iBaseView){

         this.iBaseView=iBaseView;
     }
     public T  getiBaseView(){
         return iBaseView;
     }
}
