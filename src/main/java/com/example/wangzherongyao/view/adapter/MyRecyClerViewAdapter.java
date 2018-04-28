package com.example.wangzherongyao.view.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangzherongyao.R;
import com.example.wangzherongyao.model.bean.HeadlineBean;
import com.example.wangzherongyao.util.constant.constants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by P7XXTM1-G on 2018/4/27.
 */

public class MyRecyClerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private final ImageLoader instance;
    private Context context;
    private List<HeadlineBean.DataBeanX.DataBean> list = new ArrayList<>();
    private OnItemClickLitener onItemClickLitener;
    public MyRecyClerViewAdapter(Context context) {
        this.context = context;
        instance = ImageLoader.getInstance();

    }
    public  void setList(List<HeadlineBean.DataBeanX.DataBean> list){
        this.list=list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View inflate = LayoutInflater.from(context).inflate(R.layout.twoitem, null);

            return new MyTwoHolder(inflate);
        }else{
            View inflate = LayoutInflater.from(context).inflate(R.layout.oneitem, null);

            return new MyOneHolder(inflate);

        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyOneHolder){
            ((MyOneHolder) holder).onetext.setText(list.get(position).getTitle());
            instance.displayImage(constants.URL+list.get(position).getPics().get(0),((MyOneHolder) holder).oneimg);
            ((MyOneHolder) holder).onepraise.setText(list.get(position).getViews()+"评论120个赞");
            ((MyOneHolder) holder).oneimg.setOnClickListener(this);
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickLitener.OnItemLongClickLitener(v,position);
                    return false;
                }
            });

        }else if (holder instanceof  MyTwoHolder){
            ((MyTwoHolder) holder).twotitle.setText(list.get(position).getTitle());
            instance.displayImage(constants.URL+list.get(position).getPics().get(0),((MyTwoHolder) holder).twoimgone);
            instance.displayImage(constants.URL+list.get(position).getPics().get(1),((MyTwoHolder) holder).twoimgtwo);
            instance.displayImage(constants.URL+list.get(position).getPics().get(2),((MyTwoHolder) holder).twoimgthree);
            ((MyTwoHolder) holder).twopraise.setText(list.get(position).getViews()+"评论120个赞");
            ((MyTwoHolder) holder).twoimgone.setOnClickListener(this);
            ((MyTwoHolder) holder).twoimgtwo.setOnClickListener(this);
            ((MyTwoHolder) holder).twoimgthree.setOnClickListener(this);
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickLitener.OnItemLongClickLitener(v,position);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getPics().size()>1){

            return 0;
        }else{

            return 1;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.oneimg:
                alpha(v);

                break;
            case R.id.twoimgone:

                alpha(v);
                break;
            case R.id.twoimgtwo:

                alpha(v);
                break;
            case R.id.twoimgthree:

                alpha(v);
                break;



        }


    }

    private class MyOneHolder extends RecyclerView.ViewHolder{

        private final ImageView oneimg;
        private final TextView onetext;
        private final TextView onepraise;

        public MyOneHolder(View itemView) {
            super(itemView);
            oneimg = itemView.findViewById(R.id.oneimg);
            onetext = itemView.findViewById(R.id.onetitle);
            onepraise = itemView.findViewById(R.id.onepraise);

        }


    }
    private class MyTwoHolder extends RecyclerView.ViewHolder{

        private final TextView twotitle;
        private final TextView twopraise;
        private final ImageView twoimgone;
        private final ImageView twoimgtwo;
        private final ImageView twoimgthree;

        public MyTwoHolder(View itemView) {
            super(itemView);
            twotitle = itemView.findViewById(R.id.twotitle);
            twopraise = itemView.findViewById(R.id.twopraise);
            twoimgone = itemView.findViewById(R.id.twoimgone);
            twoimgtwo = itemView.findViewById(R.id.twoimgtwo);
            twoimgthree = itemView.findViewById(R.id.twoimgthree);


        }


    }
    public void alpha(View v){

        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 1.0f, 0.0f, 1.0f);
        alpha.setDuration(2000).start();


    }
    public interface OnItemClickLitener{

        public void OnItemLongClickLitener(View v,int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }
}
