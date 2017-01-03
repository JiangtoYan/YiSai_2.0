package com.jy.yisai_20;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jy.jylibrary.base.BaseActivity;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.oguzdev.circularfloatingactionmenu.library.animation.DefaultAnimationHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.CompetitonFragment;
import fragment.DiscoveryeFragment;
import fragment.HomeFragment;
import fragment.MineFragment;
import fragment.ShootFragment;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.rb_home)
    RadioButton mRbHome;
    @BindView(R.id.rb_competition)
    RadioButton mRbCompetition;
    @BindView(R.id.rb_shoot)
    RadioButton mRbShoot;
    @BindView(R.id.rb_discovery)
    RadioButton mRbDiscovery;
    @BindView(R.id.rb_mine)
    RadioButton mRbMine;
    @BindView(R.id.rg_bottom)
    RadioGroup mRgBottom;


    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        //initFAB();
    }


    @Override
    protected void initView() {
        super.initView();
        //模拟点击首页
        mRgBottom.getChildAt(0).performClick();
    }

    @Override
    protected void loadDatas() {
        super.loadDatas();
    }

    @OnClick({R.id.rb_home, R.id.rb_competition, R.id.rb_shoot, R.id.rb_discovery, R.id.rb_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                showFragment(R.id.fl_occupy, new HomeFragment());
                break;
            case R.id.rb_competition:
                showFragment(R.id.fl_occupy, new CompetitonFragment());
                break;
            case R.id.rb_shoot:
                showFragment(R.id.fl_occupy, new ShootFragment());
                break;
            case R.id.rb_discovery:
                showFragment(R.id.fl_occupy, new DiscoveryeFragment());
                break;
            case R.id.rb_mine:
                showFragment(R.id.fl_occupy, new MineFragment());
                break;
        }
    }

    private void initFAB() {
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.mipmap.ic_launcher);
        //FloatingActionButton
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setPosition(5)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView itemIcon = new ImageView(this);
        ImageView itemIcon2 = new ImageView(this);
        ImageView itemIcon3 = new ImageView(this);
        ImageView itemIcon4 = new ImageView(this);

        itemIcon.setImageResource(R.mipmap.ic_launcher);
        itemIcon2.setImageResource(R.mipmap.ic_launcher);
        itemIcon3.setImageResource(R.mipmap.ic_launcher);
        itemIcon4.setImageResource(R.mipmap.ic_launcher);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(250,250);

        SubActionButton button1 = itemBuilder.setContentView(itemIcon).setTheme(2).setLayoutParams(layoutParams).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).setTheme(0).setLayoutParams(layoutParams).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).setTheme(1).setLayoutParams(layoutParams).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(actionButton)
                .setStartAngle(200)
                .setEndAngle(340)
                .build();

        DefaultAnimationHandler defaultAnimationHandler;

        Point actionViewCenter = actionMenu.getActionViewCenter();
        Log.d(TAG, "initFAB: " + actionViewCenter);
    }
}
