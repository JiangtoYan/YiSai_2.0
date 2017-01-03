package fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jy.jylibrary.base.BaseFragment;
import com.jy.yisai_20.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.CompetitionRecycleViewAdapter;
import app.AppConfig;
import butterknife.BindView;
import butterknife.ButterKnife;
import entity.EntityCompetition;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/26.
 */
public class CompetitonFragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    @BindView(R.id.recycleView)
    PullLoadMoreRecyclerView mRecycleView;

    private static final String TAG = "CompetitonFragment";
    private List<EntityCompetition> CompetitionDatas = new ArrayList<>();
    private CompetitionRecycleViewAdapter CRVadapter;
    private AppConfig appConfig;
    private String url;

    //加载起始位置，一次加载数量
    private int loadFrom = 0;
    private int loadNum = 10;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected int getCntentId() {
        return R.layout.fragment_competition;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        CRVadapter = new CompetitionRecycleViewAdapter(getContext());
        mRecycleView.setLinearLayout();
        mRecycleView.setOnPullLoadMoreListener(this);
        mRecycleView.setAdapter(CRVadapter);
    }

    @Override
    protected void loadDatas() {
        super.loadDatas();
        //初始化appConfig
        appConfig = AppConfig.getInstance();
        loadCompetitiomData(0,10);
    }

    //下拉刷新和上拉加载
    @Override
    public void onRefresh() {
        loadCompetitiomData(0,10);
        mRecycleView.setPullLoadMoreCompleted();
    }

    @Override
    public void onLoadMore() {
        //加载后十条
        loadFrom += loadNum;
        loadCompetitiomData(loadFrom,loadNum);
        if(CompetitionDatas.size() == 0){
            mRecycleView.setFooterViewText("已经到底了");
            mRecycleView.setPullLoadMoreCompleted();
            return;
        }else {
            mRecycleView.setFooterViewText("loading...");
        }
        mRecycleView.setPullLoadMoreCompleted();
    }

    /**
     *  加载数据
     * @param start 记录开始的位置
     * @param num 获取的行数
     */
    public void loadCompetitiomData(final int start, final int num){
        url = appConfig.getApiUrl("competition_info");
        HashMap<String, String> params = new HashMap<>();
        params.put("a", "list");
        params.put("start",String.valueOf(start));
        params.put("num", String.valueOf(num));
        if (url != null) {
            OkGo.get(url)
                    .params(params)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            try {
                                JSONObject jo = new JSONObject(s).getJSONObject("data");
                                JSONArray ja = jo.getJSONArray("lst_competition_info");
                                TypeToken<List<EntityCompetition>> tt = new TypeToken<List<EntityCompetition>>() {
                                };
                                //如果下拉加载，清空数据后再添加
                                if (start == 0){
                                    CRVadapter.removeAllDatas();
                                }
                                CompetitionDatas = new Gson().fromJson(ja.toString(), tt.getType());
                                if (CompetitionDatas.size() == 0){
                                    mRecycleView.setFooterViewText("已经到底了");
                                    mRecycleView.setPullLoadMoreCompleted();
                                    return;
                                }
                                Log.d(TAG, "onSuccess: " + CompetitionDatas);
                                Log.d(TAG, "onSuccess: " + CompetitionDatas.size());
                                Log.d(TAG, "onSuccess: " + "start" + start + "num" + num);
                                //                                CommonAdapter<EntityCompetition> commonAdapter = new CommonAdapter<EntityCompetition>(getContext(), R.layout.item_competiton, CompetitionDatas) {
//
//
//
//                                    @Override
//                                    protected void convert(ViewHolder holder, EntityCompetition entityCompetition, int position) {
//                                        EntityCompetition dataBean = CompetitionDatas.get(position);
//
//                                        //比赛图片
//                                        Glide.with(getContext())
//                                                .load(dataBean.getCover_plan())
//                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                                .thumbnail(0.1f)
//                                                .crossFade()
//                                                .into((ImageView) holder.getView(R.id.iv_competiton));
//
//                                        //标题和报名时间
//                                        holder.setText(R.id.tv_title, dataBean.getMatch_name());
//                                        holder.setText(R.id.tv_time, dataBean.getStart_time() + "-" + dataBean.getRegistration_time_end());
//                                        holder.setText(R.id.tv_register, "已有" + dataBean.getRegister_number() + "人报名");
//                                    }
//
//
//                                }
                                CRVadapter.addDatas(CompetitionDatas);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }

}
