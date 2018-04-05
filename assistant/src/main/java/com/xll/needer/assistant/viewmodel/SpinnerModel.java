package com.xll.needer.assistant.viewmodel;

/**
 * Created by Administrator on 2018/3/26.
 */

public class SpinnerModel {
    private String[] consumptionLevel;

    public String[] getConsumptionLevel() {
        consumptionLevel = new String[]{
                "理智，正确的消费",
                "不必要的消费",
                "错误的消费"
        };
        return consumptionLevel;
    }

    public void setConsumptionLevel(String[] consumptionLevel) {
        this.consumptionLevel = consumptionLevel;
    }
}
