package com.xll.needer.assistant.Utils;

import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

/**
 * Created by Administrator on 2018/4/2.
 */

public class ResUtils {

    public static String getString(@StringRes int resId) {
        return ContextUtils.getInstance().getApplicationContext().getResources().getString(resId);
    }

    public static String[] getStringArray(@ArrayRes int arrId) {
        return ContextUtils.getInstance().getApplicationContext().getResources().getStringArray(arrId);
    }
}
