package com.xll.needer.assistant.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.xll.needer.assistant.BR;

/**
 * Created by Administrator on 2018/4/7.
 */

public class PhotoItemModel extends BaseObservable{
    private String imgUri;

    private String name;

    private String count;

    @Bindable
    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
        notifyPropertyChanged(BR.imgUri);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
        notifyPropertyChanged(BR.count);
    }
}
