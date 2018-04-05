package com.xll.needer.assistant.eventhandler;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.xll.needer.assistant.ui.AppLockActivity;
import com.xll.needer.assistant.ui.KnowledgeActivity;
import com.xll.needer.assistant.ui.LedgeActivity;
import com.xll.needer.assistant.ui.PhotoActivity;
import com.xll.needer.assistant.ui.PlanActivity;
import com.xll.needer.assistant.ui.SportActivity;

/**
 * Created by Administrator on 2018/3/25.
 */

public class MainHandler {

    private final Activity activity;
    public MainHandler(Activity activity) {
        this.activity = activity;
    }

    public void onClickLedge(View view) {
        Intent intent = new Intent(activity, LedgeActivity.class);
        activity.startActivity(intent);
    }

    public void onClickPhoto(View view) {
        Intent intent = new Intent(activity, PhotoActivity.class);
        activity.startActivity(intent);
    }

    public void onClickSport(View view) {
        Intent intent = new Intent(activity, SportActivity.class);
        activity.startActivity(intent);
    }

    public void onClickKnowledge(View view) {
        Intent intent = new Intent(activity, KnowledgeActivity.class);
        activity.startActivity(intent);
    }

    public void onClickPlan(View view) {
        Intent intent = new Intent(activity, PlanActivity.class);
        activity.startActivity(intent);
    }

    public void onClickAppLock(View view) {
        Intent intent = new Intent(activity, AppLockActivity.class);
        activity.startActivity(intent);
    }
}
