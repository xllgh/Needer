package com.xll.needer.assistant.sugar;

import com.orm.SugarRecord;

/**
 * Created by Administrator on 2018/3/28.
 */

public class ConsumptionSugar extends SugarRecord<ConsumptionSugar>{
    private String goods;
    private float price;
    private long date;
    private int level;
    private int year;
    private int month;
    private String day;
    private float sum;
    private float groupSum;


    public String getGoods() {
        return goods;
    }

    public ConsumptionSugar setGoods(String goods) {
        this.goods = goods;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public ConsumptionSugar setPrice(float price) {
        this.price = price;
        return this;
    }

    public long getDate() {
        return date;
    }

    public ConsumptionSugar setDate(long date) {
        this.date = date;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public ConsumptionSugar setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getYear() {
        return year;
    }

    public ConsumptionSugar setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public ConsumptionSugar setMonth(int month) {
        this.month = month;
        return this;
    }

    public String getDay() {
        return day;
    }

    public ConsumptionSugar setDay(String day) {
        this.day = day;
        return this;
    }

    public float getSum() {
        return sum;
    }

    public ConsumptionSugar setSum(float sum) {
        this.sum = sum;
        return this;
    }

    public float getGroupSum() {
        return groupSum;
    }

    public void setGroupSum(float groupSum) {
        this.groupSum = groupSum;
    }

    @Override
    public String toString() {
        return "ConsumptionSugar{" +
                "goods='" + goods + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", level=" + level +
                ", year=" + year +
                ", month=" + month +
                ", day='" + day + '\'' +
                ", sum=" + sum +
                ", groupSum=" + groupSum +
                '}';
    }
}
