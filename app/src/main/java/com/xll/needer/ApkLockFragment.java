package com.xll.needer;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xll.needer.entity.AppUsageInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToIntFunction;

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by Administrator on 2017/12/27.
 */

public class ApkLockFragment extends Fragment{

    private static final String TAG = "ApkLockFragment";

    private ColumnChartView chartView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.apk_lock_fragment, container, false);
        Log.i(TAG, "SDK VERSION:" + Build.VERSION.SDK_INT);
        initView(rootView);
        getAllApk(listInstalledApk());
        return rootView;
    }

    private void initView(View rootView) {
        chartView = rootView.findViewById(R.id.chart);
    }

    PackageManager pm;

    public List<ResolveInfo> listInstalledApk() {
        pm = this.getActivity().getPackageManager();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> list = new ArrayList<>();
        list.addAll(pm.queryIntentActivities(intent, PackageManager.SIGNATURE_MATCH));
        Log.e(TAG, "listInstalledApk size:" + list.size());
        return list;
    }

    private Field getReflectValue(Object object, String fieldName){
        Field result = null;
        try {
            result = object.getClass().getField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getAllApk(@NonNull List<ResolveInfo> installList)  {
        UsageStatsManager usageStatsManager = (UsageStatsManager) this.getActivity().getSystemService("usagestats");

        Calendar beginCal = Calendar.getInstance();
        beginCal.set(Calendar.DATE, 31);
        beginCal.set(Calendar.MONTH, 12);
        beginCal.set(Calendar.YEAR, 1970);
        Map<String, UsageStats> map = usageStatsManager.queryAndAggregateUsageStats(beginCal.getTimeInMillis(), System.currentTimeMillis());
        List<AppUsageInfo> appUsageInfoList = new ArrayList<>();
        if (map == null) {
            return;
        }

        for(ResolveInfo resolveInfo : installList) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            String key = null;
            if (activityInfo == null || (key = activityInfo.packageName) == null) {
                continue;
            }
            UsageStats us = map.get(key);
            if (us != null) {
                appUsageInfoList.add(new AppUsageInfo(us, resolveInfo));
            }
        }

        Collections.sort(appUsageInfoList);
        List<SubcolumnValue> values ;
        List<Column> columns = new ArrayList<>();
        List<AxisValue> axisValues = new ArrayList<>();

        for (int i = 0; i <10; i++) {
            UsageStats usageStats = appUsageInfoList.get(i).getUsageStats();
            values = new ArrayList<>();
            long totalTime = usageStats.getTotalTimeInForeground();
            int count = 0;
            AxisValue axisValue = new AxisValue(i);
            axisValue.setLabel(appUsageInfoList.get(i).getResolveInfo().loadLabel(pm).toString());
            axisValues.add(axisValue);
            values.add(new SubcolumnValue(totalTime,
                    getResources().getColor(android.R.color.holo_blue_dark)));
            Column column = new Column(values);
            columns.add(column);
        }
        ColumnChartData data = new ColumnChartData(columns);
        Axis axisX = new Axis();
        axisX.setHasTiltedLabels(true);
        axisX.setTypeface(Typeface.SANS_SERIF);
        axisX.setValues(axisValues);
        Axis axisY = new Axis().setHasLines(true);
        axisY.setName(getString(R.string.app_foregroud_time));
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        chartView.setColumnChartData(data);
        chartView.setOnValueTouchListener(new ColumnSelect());
    }

    class ColumnSelect implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {

        }

        @Override
        public void onValueDeselected() {

        }
    }

    class UsageComparator implements Comparator {

        @Override
        public int compare(@NonNull Object o1,@NonNull Object o2) {
            if (o1 instanceof UsageStats && o2 instanceof UsageStats) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    UsageStats usageStats1 = (UsageStats)o1;
                    UsageStats usageStats2 = (UsageStats) o2;
                    return (int) (usageStats2.getTotalTimeInForeground() - usageStats1.getTotalTimeInForeground());

                }
            }
            return 0;
        }
    }
}
