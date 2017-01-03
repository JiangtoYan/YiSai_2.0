package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jy.yisai_20.R;

import java.util.ArrayList;
import java.util.List;

import entity.EntityCompetition;

/**
 * Created by Administrator on 2016/12/28.
 */

public class CompetitionRecycleViewAdapter extends RecyclerView.Adapter<CompetitionRecycleViewAdapter.ViewHolder>{
    private static final String TAG = "CompetitionRecycleViewA";
    private Context mContext;
    public List<EntityCompetition> datas;


    public CompetitionRecycleViewAdapter(Context context) {
        this.mContext = context;
        this.datas = new ArrayList<>();
    }

    public void setDatas(List<EntityCompetition> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    public void addDatas(List<EntityCompetition> datas) {
        this.datas.addAll(datas);
        Log.d(TAG, "addDatas: " + this.datas.size());
        this.notifyDataSetChanged();
    }

    public void removeAllDatas() {
        this.datas.clear();
        this.notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_competiton,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EntityCompetition dataBean = datas.get(position);
        holder.tv_title.setText(dataBean.getMatch_name());
        holder.tv_time.setText(dataBean.getStart_time() + "-" + dataBean.getRegistration_time_end());
        holder.tv_register.setText("已有" + dataBean.getRegister_number() + "人报名");

        //比赛图片
        Glide.with(mContext)
                .load(dataBean.getCover_plan())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .crossFade()
                .into(holder.iv_competiton);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_competiton;
        private TextView tv_title,tv_time,tv_register;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_competiton = (ImageView) itemView.findViewById(R.id.iv_competiton);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_register = (TextView) itemView.findViewById(R.id.tv_register);
        }
    }
}
