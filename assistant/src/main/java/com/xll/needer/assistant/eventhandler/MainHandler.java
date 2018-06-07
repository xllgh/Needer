package com.xll.needer.assistant.eventhandler;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.xll.needer.assistant.ui.AppLockFragment;
import com.xll.needer.assistant.ui.MurmurFragment;
import com.xll.needer.assistant.ui.LedgeFragment;
import com.xll.needer.assistant.ui.PhotoActivity;
import com.xll.needer.assistant.ui.PlanActivity;
import com.xll.needer.assistant.ui.SportUtils;

/**
 * Created by Administrator on 2018/3/25.
 */

public class MainHandler {

    private final Activity activity;
    public MainHandler(Activity activity) {
        this.activity = activity;
    }

    public void onClickLedge(View view) {
        Intent intent = new Intent(activity, LedgeFragment.class);
        activity.startActivity(intent);
    }

    public void onClickPhoto(View view) {
        Intent intent = new Intent(activity, PhotoActivity.class);
        activity.startActivity(intent);
    }

    public void onClickSport(View view) {
        Intent intent = new Intent(activity, SportUtils.class);
        activity.startActivity(intent);
    }

    public void onClickKnowledge(View view) {
        Intent intent = new Intent(activity, MurmurFragment.class);
        activity.startActivity(intent);
    }

    public void onClickPlan(View view) {
        Intent intent = new Intent(activity, PlanActivity.class);
        activity.startActivity(intent);
    }

    public void onClickAppLock(View view) {
        Intent intent = new Intent(activity, AppLockFragment.class);
        activity.startActivity(intent);
    }
}
