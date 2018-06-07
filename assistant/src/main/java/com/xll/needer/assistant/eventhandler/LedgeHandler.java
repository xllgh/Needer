package com.xll.needer.assistant.eventhandler;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.xll.needer.assistant.R;
import com.xll.needer.assistant.Utils.DatabaseUtils;
import com.xll.needer.assistant.Utils.ResUtils;
import com.xll.needer.assistant.sugar.ConsumptionSugar;
import com.xll.needer.assistant.model.LedgeModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Administrator on 2018/3/25.
 */

public class LedgeHandler {
    private final Activity activity;
    private final LedgeModel model;
    private static final String TAG = "LedgeHandler";

    public LedgeHandler(@NonNull Activity activity, @NonNull LedgeModel model) {
        this.activity = activity;
        this.model = model;
        init();
    }

    private void init() {
        if (model == null) {
            return;
        }
        model.consumptionDate.set(getDay(new Date()));
        loadChart();
    }

    private void loadChart() {
        refreshChartView();
        refreshPieChartView();
    }

    private void refreshPieChartView() {
        List<ConsumptionSugar> list = DatabaseUtils.getConsumption(-1, -1, "level", "sum");
        List<ConsumptionSugar> sumList = DatabaseUtils.getConsumption(-1, -1, null, null);
        if (list == null || list.size() == 0 || sumList == null || sumList.size() == 0) {
            return;
        }
        Log.i(TAG, "refreshPieChartView:" + list);
        ConsumptionSugar sumSugar = sumList.get(0);
        float total = sumSugar.getGroupSum();
        List<SliceValue> values = new ArrayList<>();
        String[] arr = ResUtils.getStringArray(R.array.consumptionLevel);
        for (int i = 0; i < list.size() && arr.length >= list.size(); i++) {
            ConsumptionSugar sugar = list.get(i);
            SliceValue value = new SliceValue(sugar.getGroupSum() / total, R.color.colorPrimary);
            value.setLabel(arr[i] + ":" + sugar.getGroupSum());
            values.add(value);
        }

        PieChartData data = new PieChartData(values);
        data.setHasLabels(true);
        model.pieChartData.set(data);
    }

    private void refreshChartView() {
        List<ConsumptionSugar> list = DatabaseUtils.getConsumption(-1, -1, "day", "date");
        if (list == null || list.size() == 0) {
            return;
        }
        Log.i(TAG, "refreshChartView:" + list);
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;

        List<AxisValue> axisXValue = new ArrayList();
        int i = 0;
        for (ConsumptionSugar consumption : list) {
            values = new ArrayList<>();
            values.add(new SubcolumnValue(consumption.getGroupSum(), R.color.colorPrimary));
            Column column = new Column(values);
            columns.add(column);
            AxisValue value = new AxisValue(i++);
            value.setLabel(consumption.getDay());
            axisXValue.add(value);
        }
        ColumnChartData data = new ColumnChartData(columns);

        Axis axisX = new Axis(axisXValue).setHasLines(true);
        axisX.setHasTiltedLabels(true);
        axisX.setName(ResUtils.getString(R.string.axisTime));
        axisX.setTypeface(Typeface.DEFAULT);

        Axis axisY = new Axis().setHasLines(true);
        axisY.setName(ResUtils.getString(R.string.axisConsumption));
        data.setAxisYLeft(axisY);
        data.setAxisXBottom(axisX);
        model.chartData.set(data);
    }

    public void onClickConsumptionDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(activity, listener, year, month, day);
        dialog.show();
    }


    public void onSave(View view) {
        String date = model.consumptionDate.get();
        String[] dateInfo = date.split("-");
        long millis = 0L;
        int year = 0;
        int month = 0;
        if (dateInfo.length == 3) {
            year = Integer.valueOf(dateInfo[0]);
            month = Integer.valueOf(dateInfo[1]) - 1;
            int day = Integer.valueOf(dateInfo[2]);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            millis = calendar.getTimeInMillis();
        }
        String goods = model.goods.get();
        String price = model.price.get();
        if (TextUtils.isEmpty(goods) || TextUtils.isEmpty(price)) {
            Toast.makeText(activity, R.string.ledge_content_null_prompt, Toast.LENGTH_LONG).show();
            return;
        }



        ConsumptionSugar consumptionSugar = new ConsumptionSugar()
                .setGoods(goods)
                .setPrice(Float.valueOf(price))
                .setDate(millis)
                .setLevel(model.levelSelectPosition.get())
                .setYear(year)
                .setMonth(month)
                .setDay(getDay(new Date(millis)));
        consumptionSugar.save();
        loadChart();
    }


    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);
            model.consumptionDate.set(getDay(calendar.getTime()));
        }
    };

    private String getDay(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat(activity.getString(R.string.dateFormat), Locale.CHINA);
        return sdf.format(time);
    }

    @BindingAdapter("android:setChartData")
    public static void setData(ColumnChartView view, ColumnChartData chartData) {
        if (chartData == null) {
            return;
        }
        view.setColumnChartData(chartData);
    }

    @BindingAdapter("android:setPieChartData")
    public static void setPieData(PieChartView view, PieChartData pieChartData) {
        if (pieChartData != null) {
            view.setPieChartData(pieChartData);
        }
    }
}
