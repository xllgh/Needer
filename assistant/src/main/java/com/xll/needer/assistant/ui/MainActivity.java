package com.xll.needer.assistant.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xll.needer.assistant.R;
import com.xll.needer.assistant.databinding.ActivityMainBinding;
import com.xll.needer.assistant.eventhandler.MainHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainHandler handler = new MainHandler(this);
        binding.setMainHandler(handler);
    }
}
