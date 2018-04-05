package com.xll.needer.assistant.Utils;

import android.support.annotation.StringRes;

/**
 * Created by Administrator on 2018/4/2.
 */

public class ResUtils {

    public static String getString(@StringRes int resId) {
        return ContextUtils.getInstance().getApplicationContext().getResources().getString(resId);
    }
}
