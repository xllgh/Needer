package com.xll.needer.fortest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/1/19.
 */

public class  MyFragment extends Fragment {

    private static final String TAG = "MyFragment";

    public MyFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: " + getArguments());
        View view = inflater.inflate(R.layout.my_fragment, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView :" + getArguments());
        super.onDestroyView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            Log.i(TAG, "onHiddenChange:" + getArguments());
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            Log.i(TAG, "setUserVisibleHint: " + getArguments());
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onViewCreated");
        super.onResume();
    }
}
