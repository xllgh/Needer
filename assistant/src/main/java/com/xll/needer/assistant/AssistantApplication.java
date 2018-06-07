package com.xll.needer.assistant;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.orm.SugarApp;
import com.xll.needer.assistant.Utils.ContextUtils;

/**
 * Created by Administrator on 2018/4/2.
 */

public class AssistantApplication extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtils.getInstance().subcribeContext(this);
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}
