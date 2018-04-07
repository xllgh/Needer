package com.xll.needer.assistant.bean;

import android.databinding.ObservableArrayList;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class PhotoModel{

    private ObservableArrayList list = new ObservableArrayList();

    public ObservableArrayList getList() {
        return list;
    }

    public void setList(ObservableArrayList list) {
        this.list = list;
    }

    public void setList(List<PhotoItemModel> list) {
        this.list.addAll(list);
    }
}
