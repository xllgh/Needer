package com.xll.needer.assistant.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.xll.needer.assistant.R;
import com.xll.needer.assistant.databinding.ActivityMainBinding;
import com.xll.needer.assistant.eventhandler.MainHandler;

public class MainActivity  extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainHandler handler = new MainHandler(this);
        binding.setMainHandler(handler);
    }
}
