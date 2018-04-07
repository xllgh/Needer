package com.xll.needer.assistant.eventhandler;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.xll.needer.assistant.adapter.PhotoAdapter;
import com.xll.needer.assistant.bean.PhotoItemModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class PhotoItemHandler {

    private static Activity activity;

    public PhotoItemHandler(@NonNull Activity activity) {
        this.activity = activity;
    }

    @BindingAdapter("android:setPhotoAdapter")
    public static void setAdapter(RecyclerView view, List<PhotoItemModel> list) {
        PhotoAdapter adapter = new PhotoAdapter(activity, list);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(activity));
    }

    @BindingAdapter("android:setImage")
    public static void setImage(ImageView view, String uri) {
        view.setImageBitmap(BitmapFactory.decodeFile(uri));
    }
}
