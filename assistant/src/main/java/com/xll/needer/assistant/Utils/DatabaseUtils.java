package com.xll.needer.assistant.Utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.xll.needer.assistant.sugar.ConsumptionSugar;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class DatabaseUtils {
    private final static String TAG = "DatabaseUtils";

    public static void saveConsumption(String goods, float price, long date, int level) {

    }

    /**
     * @param startTime 开始时间
     * @param endTime   结束时间
     *                  当 startTime == endTime == -1,返回整个列表
     **/
    public static List<ConsumptionSugar> getConsumption(long startTime, long endTime, String groupBy, String orderBy) {
        String whereClause = null;
        @NonNull
        final String[] whereArgs;
        groupBy = TextUtils.isEmpty(groupBy) ? "" : " GROUP BY " + groupBy;
        orderBy = TextUtils.isEmpty(orderBy) ? "" : " ORDER BY " + orderBy;

        List<ConsumptionSugar> list;
        if (startTime != -1 && endTime != -1) {
            whereClause = " date >= ? and date <= ? ";
        }
        String tableName = ConsumptionSugar.getTableName(ConsumptionSugar.class);
        String query;
        if (whereClause != null) {
            whereArgs = new String[]{String.valueOf(startTime), String.valueOf(endTime)};
            query = "select goods, GROUP_SUM, sum(price) as GROUP_SUM, day, level, date, year, month from "+ tableName +" where " +
                    whereClause + groupBy + orderBy;
            list = ConsumptionSugar.findWithQuery(ConsumptionSugar.class, query, whereArgs);
        } else {
            query = "select goods, GROUP_SUM, sum(price) as GROUP_SUM, day, level, date, year, month from "+ tableName +
                    groupBy + orderBy;
            list = ConsumptionSugar.findWithQuery(ConsumptionSugar.class, query);
        }
        Log.i(TAG, "getConsumption:" + list);
        return list;
    }

}
