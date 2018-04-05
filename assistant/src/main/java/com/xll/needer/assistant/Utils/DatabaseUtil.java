package com.xll.needer.assistant.Utils;

import android.util.Log;

import com.xll.needer.assistant.sugar.ConsumptionSugar;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class DatabaseUtil {
    private final static String TAG = "DatabaseUtil";

    public static void saveConsumption(String goods, float price, long date, int level) {

    }

    /**
     * @param startTime 开始时间
     * @param endTime   结束时间
     *                  当 startTime == endTime == -1,返回整个列表
     **/
    public static List<ConsumptionSugar> getConsumption(long startTime, long endTime) {

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = "day";
        String orderBy = "date";

        List<ConsumptionSugar> list;
        if (startTime != -1 && endTime != -1) {
            whereClause = " date >= ? and date <= ? ";
        }
        String tableName = ConsumptionSugar.getTableName(ConsumptionSugar.class);
        String query;
        if (whereClause != null) {
            whereArgs = new String[]{String.valueOf(startTime), String.valueOf(endTime), groupBy, orderBy};
            query = "select goods, sum(price), day, level, date, year, month from "+ tableName +" where " +
                    whereClause + " GROUP BY ? " + " ORDER BY ? ";
        } else {
            whereArgs = new String[]{groupBy, orderBy};
            query = "select goods, sum, sum(price) as SUM, day, level, date, year, month from "+ tableName +
                    " GROUP BY day  ORDER BY  date ";
        }
        Log.i(TAG, "query:" + query);
//        Iterator<ConsumptionSugar> iterator = ConsumptionSugar.findAsIterator(ConsumptionSugar.class, query);
//        while(iterator.hasNext()) {
//            ConsumptionSugar sugar = iterator.next();
//            Log.i(TAG , "sum iterator:" + sugar.getSum());
//        }

        list = ConsumptionSugar.findWithQuery(ConsumptionSugar.class, query/*, whereArgs*/);
        Log.i(TAG, "getConsumption:" + list);
        return list;
    }

}
