package com.xll.needer.assistant.eventhandler;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.xll.needer.assistant.R;
import com.xll.needer.assistant.Utils.DatabaseUtil;
import com.xll.needer.assistant.Utils.ResUtils;
import com.xll.needer.assistant.sugar.ConsumptionSugar;
import com.xll.needer.assistant.viewmodel.LedgeModel;

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
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

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

    public void init() {
        if (model == null) {
            return;
        }
        model.consumptionDate.set(getDay(new Date()));
        refreshChartView();
    }

    private void refreshChartView() {
        List<ConsumptionSugar> list = DatabaseUtil.getConsumption(-1, -1);
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
            values.add(new SubcolumnValue(consumption.getSum()));
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

        ConsumptionSugar consumptionSugar = new ConsumptionSugar()
                .setGoods(model.goods.get())
                .setPrice(Float.valueOf(model.price.get()))
                .setDate(millis)
                .setLevel(model.levelSelectPosition.get())
                .setYear(year)
                .setMonth(month)
                .setDay(getDay(new Date(millis)));
        consumptionSugar.save();
        refreshChartView();
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

    @BindingAdapter("android:setCharData")
    public static void setData(ColumnChartView view, ColumnChartData chartData) {
        if (chartData == null) {
            return;
        }
        view.setColumnChartData(chartData);
    }
}
