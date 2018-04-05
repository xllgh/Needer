package com.xll.needer.assistant.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import lecho.lib.hellocharts.model.ColumnChartData;


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

    public LedgeModel() {
        consumptionLevel.add("理智，正确的消费");
        consumptionLevel.add("不必要的消费");
        consumptionLevel.add("错误的消费");
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
}
