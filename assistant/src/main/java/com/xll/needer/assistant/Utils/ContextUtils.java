package com.xll.needer.assistant.Utils;

import android.content.Context;

/**
 * Created by Administrator on 2018/4/2.
 */

public class ContextUtils {

    private static ContextUtils Instance = new ContextUtils();

    private Context applicationContext;

    public static ContextUtils getInstance() {
        return Instance;
    }

    public void subcribeContext(Context context) {
         applicationContext = context.getApplicationContext();
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }
}
