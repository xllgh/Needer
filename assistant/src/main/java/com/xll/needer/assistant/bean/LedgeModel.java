package com.xll.needer.assistant.bean;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.xll.needer.assistant.R;
import com.xll.needer.assistant.Utils.ResUtils;

import java.util.Collections;

import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;


/**
 * Created by Administrator on 2018/3/25.
 */

public class LedgeModel {
    public ObservableField<String> consumptionDate = new ObservableField<>();

    public ObservableField<String> goods = new ObservableField<>();

    public ObservableField<String> price = new ObservableField<>();

    public ObservableField<String> level = new ObservableField<>();

    public ObservableArrayList<String> consumptionLevel = new ObservableArrayList<String>();

    public ObservableInt levelSelectPosition = new ObservableInt();

    public ObservableField<ColumnChartData> chartData = new ObservableField<>();

    public ObservableField<PieChartData> pieChartData = new ObservableField<>();

    public ObservableBoolean recordLedgeVisibility = new ObservableBoolean(true);

    public LedgeModel() {
        String[] resArray = ResUtils.getStringArray(R.array.consumptionLevel);
        Collections.addAll(consumptionLevel, resArray);
    }

    public ObservableField<String> getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(ObservableField<String> consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public ObservableField<String> getGoods() {
        return goods;
    }

    public void setGoods(ObservableField<String> goods) {
        this.goods = goods;
    }

    public ObservableField<String> getPrice() {
        return price;
    }

    public void setPrice(ObservableField<String> price) {
        this.price = price;
    }

    public ObservableField<String> getLevel() {
        return level;
    }

    public void setLevel(ObservableField<String> level) {
        this.level = level;
    }

    public ObservableArrayList<String> getConsumptionLevel() {
        return consumptionLevel;
    }

    public void setConsumptionLevel(ObservableArrayList<String> consumptionLevel) {
        this.consumptionLevel = consumptionLevel;
    }

    public ObservableInt getLevelSelectPosition() {
        return levelSelectPosition;
    }

    public void setLevelSelectPosition(ObservableInt levelSelectPosition) {
        this.levelSelectPosition = levelSelectPosition;
    }

    public ObservableField<ColumnChartData> getChartData() {
        return chartData;
    }

    public void setChartData(ObservableField<ColumnChartData> chartData) {
        this.chartData = chartData;
    }

    public ObservableField<PieChartData> getPieChartData() {
        return pieChartData;
    }

    public void setPieChartData(ObservableField<PieChartData> pieChartData) {
        this.pieChartData = pieChartData;
    }

    public ObservableBoolean getRecordLedgeVisibility() {
        return recordLedgeVisibility;
    }

    public void setRecordLedgeVisibility(boolean recordLedgeVisibility) {
        this.recordLedgeVisibility.set(recordLedgeVisibility);
    }
}
