package com.xll.needer.assistant.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xll.needer.assistant.R;
import com.xll.needer.assistant.bean.PhotoItemModel;
import com.xll.needer.assistant.databinding.PhotoItemBinding;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private static final String TAG = "PhotoAdapter";

    private List<PhotoItemModel> list;

    @NonNull
    private LayoutInflater inflater;

    public PhotoAdapter(@NonNull Context context,@NonNull List<PhotoItemModel> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PhotoItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.photo_item, parent, false);
        return new PhotoHolder(binding);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount:" + list.size());
        return list.size();
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        PhotoItemModel bean = list.get(position);
        holder.getBinding().setPhotoItemModel(bean);
    }

    static class PhotoHolder extends RecyclerView.ViewHolder {

        private PhotoItemBinding binding;

        public PhotoHolder(PhotoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public PhotoItemBinding getBinding() {
            return binding;
        }

        public void setBinding(PhotoItemBinding binding) {
            this.binding = binding;
        }
    }
}
